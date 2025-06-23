import { Component, OnInit } from '@angular/core';
import { Book } from '../../../model/class';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { UserCartService } from './service/user-cart.service';
import { Modal } from 'bootstrap';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-cart',
  imports: [NgIf, NgFor, FormsModule],
  templateUrl: './user-cart.component.html',
  styleUrl: './user-cart.component.css',
})
export class UserCartComponent implements OnInit {
  userInfo: any = {}; // User profile info
  cartItems: any[] = [];
  totalPrice: number = 0;
  userId: number = Number(localStorage.getItem('id')); // Assuming user ID stored in localStorage
  userAddress: string = ''; // Address for the order
  userContact: string = ''; // Contact for the order

  constructor(
    private orderService: UserCartService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    const cart = localStorage.getItem('cartItems');
    if (cart) {
      this.cartItems = JSON.parse(cart);
      this.calculateTotal();
    }

    // Fetch user profile info (address, contact, etc.)
    this.orderService.profileInfo().subscribe({
      next: (data) => {
        this.userInfo = data; // Assuming user info comes from API
        this.userAddress = this.userInfo.address || '';
        this.userContact = this.userInfo.phoneNumber || '';
      },
      error: (err) => console.error('Failed to load user info:', err),
    });
  }

  increaseQuantity(bookId: number): void {
    const item = this.cartItems.find((book) => book.id === bookId);
    if (item) {
      item.quantity = (item.quantity || 1) + 1;
      this.updateCart();
    }
  }

  decreaseQuantity(bookId: number): void {
    const item = this.cartItems.find((book) => book.id === bookId);
    if (item) {
      item.quantity = item.quantity > 1 ? item.quantity - 1 : 1;
      this.updateCart();
    }
  }

  removeFromCart(index: number): void {
    this.cartItems.splice(index, 1);
    this.updateCart();
  }

  calculateTotal(): void {
    this.totalPrice = this.cartItems.reduce((sum, item) => {
      return sum + item.bookPrice * (item.quantity || 1);
    }, 0);
  }

  updateCart(): void {
    localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    this.calculateTotal();
  }

  openOrderModal(): void {
    // Open the modal with the current cart and user info.
    const modalEl = document.getElementById('orderModal');
    if (modalEl) {
      const modal = new Modal(modalEl);
      modal.show();
    }
  }

  placeOrder(): void {
    // Prepare order payload with user info
    if (!this.userAddress || !this.userContact) {
      alert('Please provide both address and contact number.');
      return;
    }

    const orderPayload = {
      userId: this.userId,
      bookIds: this.cartItems.flatMap((book) => Array(book.quantity || 1).fill(book.id)),
      address: this.userAddress,
      contact: this.userContact,
    };

    this.orderService.placeOrder(orderPayload).subscribe({
      next: () => {
        alert('Order placed successfully!');
        localStorage.removeItem('cartItems');
        this.cartItems = [];
        this.totalPrice = 0;
        this.router.navigate(['/user-dashboard/user-home']); // Navigate to home after order placed
      },
      error: (err) => {
        console.error('Order placement failed:', err);
        alert('Failed to place order. Please try again.');
      },
    });
  }
}