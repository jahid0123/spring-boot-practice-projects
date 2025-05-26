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
  userId: number = 1; // Replace with dynamic user ID from login if available

  constructor(private orderService: UserCartService, private router: Router) {}

  ngOnInit(): void {
    const cart = localStorage.getItem('cartItems');
    if (cart) {
      this.cartItems = JSON.parse(cart);
      this.calculateTotal();
    }
  }

  removeFromCart(index: number) {
    this.cartItems.splice(index, 1);
    localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    this.calculateTotal();
  }

  calculateTotal() {
    this.totalPrice = this.cartItems.reduce((sum, item) => sum + item.bookPrice, 0);
  }

  placeOrder() {
    const bookIds = this.cartItems.map(book => book.id);
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
        this.router.navigate(['/order-history']); // Optional
      },
      error: (err) => {
        console.error('Order placement failed:', err);
        alert('Failed to place order. Please try again.');
      }
    });
  }
}