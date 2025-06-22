import { Component, OnInit } from '@angular/core';
import { Book } from '../../../model/class';
import { UserService } from '../user-dashboard/service/user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { StorageServiceService } from '../../../core/service/storage-service.service';

@Component({
  selector: 'app-user-home',
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './user-home.component.html',
  styleUrl: './user-home.component.css',
})
export class UserHomeComponent implements OnInit {
  books: Book[] = [];
  cart: Book[] = [];
  favorites: Book[] = [];

  defaultImagePath = '12.jpg';

  

  constructor(
    private bookService: UserService,
    private router: Router,
    private storageService: StorageServiceService
  ) {}

  ngOnInit(): void {
    this.loadBooks();
    this.cart = this.storageService.getCartItems();
    this.favorites = this.storageService.getFavoriteItems();
  }

  // Load books from backend
  loadBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (data) => (this.books = data),
      error: (err) => console.error('Failed to load books:', err)
    });
  }

  // Navigate to book details page
  viewDetails(book: Book): void {
    this.router.navigate(['/user-dashboard/view-details'], {
      state: { book }
    });
  }

  // Add book to cart with quantity handling
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

  // Add to favorites with localStorage
  addToFavorites(book: Book): void {
    const stored = localStorage.getItem('favorites');
    const favorites: Book[] = stored ? JSON.parse(stored) : [];

    if (!favorites.some(b => b.id === book.id)) {
      favorites.push(book);
      localStorage.setItem('favorites', JSON.stringify(favorites));
      alert('Book added to favorites!');
    } else {
      alert('Book already in favorites!');
    }

    this.favorites = favorites;
    this.storageService.saveFavoriteItems(this.favorites);
  }

  // Handle image load failure
  onImageError(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = this.defaultImagePath;
  }

  getImageUrl(bookImageUrl: string | null): string {
    return bookImageUrl ? `http://localhost:8082/api/auth/image/${bookImageUrl}` : this.defaultImagePath;
  }
}