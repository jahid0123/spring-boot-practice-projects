import { Component } from '@angular/core';
import { Book } from '../../../model/class';
import { UserService } from '../user-dashboard/service/user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { StorageServiceService } from '../../../core/service/storage-service.service';

@Component({
  selector: 'app-user-home',
  imports: [FormsModule, NgFor],
  templateUrl: './user-home.component.html',
  styleUrl: './user-home.component.css',
})
export class UserHomeComponent {
  books: Book[] = [];

  cart: Book[] = [];

  favorites: Book[] = [];

  constructor(
    private bookService: UserService,
    private router: Router,
    private storageService: StorageServiceService
  ) {}

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe((data) => {
      this.books = data;

      this.cart = this.storageService.getCartItems();
      this.favorites = this.storageService.getFavoriteItems();
    });
  }

  viewDetails(book: any): void {
    this.router.navigate(['/user-dashboard/view-details'], { state: { book } });
  }
  // addToCart(book: Book): void {
  //   if (!this.cart.find((item) => item.id === book.id)) {
  //     this.cart.push(book);
  //     this.storageService.saveCartItems(this.cart);
  //     alert(`${book.bookName} added to cart.`);
  //   } else {
  //     alert(`${book.bookName} is already in your cart.`);
  //   }
  // }
addToCart(book: Book): void {
  const existingBook = this.cart.find(item => item.id === book.id);

  if (existingBook) {
    existingBook.quantity = (existingBook.quantity || 1) + 1;
    alert(`${book.bookName} quantity increased to ${existingBook.quantity}.`);
  } else {
    book.quantity = 1;
    this.cart.push(book);
    alert(`${book.bookName} added to cart.`);
  }

  this.storageService.saveCartItems(this.cart);
}
  addToFavorites(book: any): void {
    const stored = localStorage.getItem('favorites');
    const favorites = stored ? JSON.parse(stored) : [];

    if (!favorites.some((b: { id: any; }) => b.id === book.id)) {
      favorites.push(book);
      localStorage.setItem('favorites', JSON.stringify(favorites));
      alert('Book added to favorites!');
    } else {
      alert('Book already in favorites!');
    }
  }
}
