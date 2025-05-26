import { CommonModule, NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import Modal from 'bootstrap/js/dist/modal';
import { AddBook, Book } from '../../../model/class';
import { AddBookService } from './service/add-book.service';
import { Router } from '@angular/router';
import { UserService } from '../../user/user-dashboard/service/user.service';

@Component({
  selector: 'app-admin-add-book',
  imports: [FormsModule, CommonModule],
  templateUrl: './admin-add-book.component.html',
  styleUrl: './admin-add-book.component.css',
})
export class AdminAddBookComponent {
  book: AddBook = new AddBook();
  books: AddBook[] = [];
  getBooks: Book[] =[];
  editingIndex: number | null = null;

  constructor(
    private addBookService: AddBookService,
    private router: Router,
    private bookService: UserService
  ) {}

  ngOnInit(): void {
    // Load all books initially
    this.bookService.getAllBooks().subscribe((data) => {
      this.getBooks = data;
    });
  }

  trackByBookId(index: number, book: AddBook): number {
    return book.id;
  }

  get editingBook(): boolean {
    return this.editingIndex !== null;
  }

  openAddModal(): void {
    this.book = new AddBook();
    this.editingIndex = null;
    this.openModal();
  }

  openEditModal(book: Book): void {
    this.book = new AddBook(book);
    this.book.formatAuthorIds();
    this.editingIndex = book.id;
    this.openModal();
  }

  deleteBook(id: number): void {
 
    this.addBookService.deleteBook(id).subscribe({
      next: () => {
        
        console.log('Book deleted successfully');
      },
      error: (err) => {
        console.error('Failed to delete book:', err);
      }
    });
  }

  onSubmit(): void {
    this.book.parseAuthorIds();

    if (this.editingBook && this.editingIndex !== null) {
      // Update existing book
      this.addBookService.editBook(this.book).subscribe({
        next: (updatedBook) => {
         
          console.log('Book updated successfully');
          this.closeModal();
        },
        error: (err) => {
          console.error('Failed to update book:', err);
        }
      });
    } else {
      // Add new book
      this.addBookService.addBook(this.book).subscribe({
        next: (newBook) => {

          console.log('Book added successfully');
          this.closeModal();
        },
        error: (err) => {
          console.error('Failed to add book:', err);
        }
      });
    }
  }

  openModal(): void {
    const modal = new Modal(document.getElementById('bookModal')!);
    modal.show();
  }

  closeModal(): void {
    const modalElement = document.getElementById('bookModal');
    const modalInstance = Modal.getInstance(modalElement!);
    modalInstance?.hide();
  }
}
