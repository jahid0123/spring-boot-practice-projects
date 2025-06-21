import { Component, OnInit } from '@angular/core';
import { Book } from '../../../model/class';
import { StorageServiceService } from '../../../core/service/storage-service.service';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { UserCartService } from './service/user-cart.service';

@Component({
  selector: 'app-user-cart',
  imports: [NgIf, NgFor],
  templateUrl: './user-cart.component.html',
  styleUrl: './user-cart.component.css'
})
export class UserCartComponent implements OnInit {
   cartItems: any[] = [];
  totalPrice: number = 0;
  userId: number =  Number(localStorage.getItem('id')); // Replace with dynamic user ID from login if available

  constructor(private orderService: UserCartService, private router: Router) {}

  ngOnInit(): void {
    const cart = localStorage.getItem('cartItems');
    if (cart) {
      this.cartItems = JSON.parse(cart);
      this.calculateTotal();
    }
  }

  increaseQuantity(bookId: number): void {
    const item = this.cartItems.find(book => book.id === bookId);
    if (item) {
      item.quantity = (item.quantity || 1) + 1;
      this.updateCart();
    }
  }

  decreaseQuantity(bookId: number): void {
    const item = this.cartItems.find(book => book.id === bookId);
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
      return sum + (item.bookPrice * (item.quantity || 1));
    }, 0);
  }

  updateCart(): void {
    localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    this.calculateTotal();
  }

  placeOrder(): void {
    const bookIds = this.cartItems.flatMap(book => Array(book.quantity || 1).fill(book.id));
    const orderPayload = {
      userId: this.userId,
      bookIds: bookIds
    };

    this.orderService.placeOrder(orderPayload).subscribe({
      next: () => {
        alert('Order placed successfully!');
        localStorage.removeItem('cartItems');
        this.cartItems = [];
        this.totalPrice = 0;
        this.router.navigate(['/user-dashboard/user-home']); // Optional
      },
      error: (err) => {
        console.error('Order placement failed:', err);
        alert('Failed to place order. Please try again.');
      }
    });
  }
}
