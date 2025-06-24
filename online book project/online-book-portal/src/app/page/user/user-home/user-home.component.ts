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
  filteredBooks: Book[] = [];
  cart: Book[] = [];
  favorites: Book[] = [];
  selectedBook: Book | null = null;
  searchTerm: string = '';
  sortOption: string = 'default';
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

  loadBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (data) => {
        this.books = data;
        this.applyFilters();
      },
      error: (err) => console.error('Failed to load books:', err),
    });
  }

  viewDetails(book: Book): void {
    this.selectedBook = book;
  }

  addToCart(book: Book): void {
    const existingBook = this.cart.find((item) => item.id === book.id);

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

  addToFavorites(book: Book): void {
    const stored = localStorage.getItem('favorites');
    const favorites: Book[] = stored ? JSON.parse(stored) : [];

    if (!favorites.some((b) => b.id === book.id)) {
      favorites.push(book);
      localStorage.setItem('favorites', JSON.stringify(favorites));
      alert('Book added to favorites!');
    } else {
      alert('Book already in favorites!');
    }

    this.favorites = favorites;
    this.storageService.saveFavoriteItems(this.favorites);
  }

  onImageError(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = this.defaultImagePath;
  }

  getImageUrl(bookImageUrl: string | null): string {
    return bookImageUrl
      ? `http://localhost:8082/api/auth/image/${bookImageUrl}`
      : this.defaultImagePath;
  }

  applyFilters(): void {
    const term = this.searchTerm.toLowerCase();

    this.filteredBooks = this.books.filter((book) => {
      const bookNameMatch = book.bookName.toLowerCase().includes(term);
      const authorMatch = book.authorNames?.some((author) =>
        author.toLowerCase().includes(term)
      );
      return bookNameMatch || authorMatch;
    });

    this.sortBooks();
  }

  sortBooks(): void {
    switch (this.sortOption) {
      case 'nameAsc':
        this.filteredBooks.sort((a, b) => a.bookName.localeCompare(b.bookName));
        break;
      case 'nameDesc':
        this.filteredBooks.sort((a, b) => b.bookName.localeCompare(a.bookName));
        break;
      case 'priceLow':
        this.filteredBooks.sort((a, b) => a.bookPrice - b.bookPrice);
        break;
      case 'priceHigh':
        this.filteredBooks.sort((a, b) => b.bookPrice - a.bookPrice);
        break;
      case 'ratingHigh':
        this.filteredBooks.sort((a, b) => b.bookRating - a.bookRating);
        break;
      default:
        this.filteredBooks = [...this.filteredBooks];
    }
  }
}