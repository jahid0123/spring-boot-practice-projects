import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AddBookService } from './service/add-book.service';
import { AddAuthorService } from '../admin-add-author/service/add-author.service';
import { CommonModule, NgFor } from '@angular/common';
import bootstrap from 'bootstrap';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin-add-book',
  imports: [ ReactiveFormsModule, CommonModule],
  templateUrl: './admin-add-book.component.html',
  styleUrl: './admin-add-book.component.css',
})
export class AdminAddBookComponent implements OnInit {
  books: any[] = [];
  allAuthors: any[] = [];
  bookForm: FormGroup;
  selectedFile: File | null = null;
  selectedFileName = '';

  bookFields = [
    { key: 'bookName', label: 'Book Name', type: 'text' },
    { key: 'bookIsbnNumber', label: 'ISBN Number', type: 'number' },
    { key: 'bookPrice', label: 'Price', type: 'number', step: '0.01' },
    { key: 'bookRating', label: 'Rating', type: 'number', min: 0, max: 5, step: '0.1' },
    { key: 'bookCategory', label: 'Category', type: 'text' },
    { key: 'bookQuantity', label: 'Quantity', type: 'number' },
    { key: 'bookAuthorIds', label: 'Select Authors', type: 'select' },
  ];

  constructor(private fb: FormBuilder, private http: HttpClient, private bookService: AddBookService,private authorService: AddAuthorService ) {
    this.bookForm = this.fb.group({
      bookName: ['', Validators.required],
      bookIsbnNumber: [null, Validators.required],
      bookPrice: [null, Validators.required],
      bookRating: [null, [Validators.required, Validators.min(0), Validators.max(5)]],
      bookCategory: ['', Validators.required],
      bookQuantity: [null, Validators.required],
      bookAuthorIds: [[], Validators.required],
    });
  }

  ngOnInit(): void {
    this.fetchAuthors();
    this.fetchBooks();
  }

  fetchAuthors(): void {
    this.allAuthors = [
      { id: 1, name: 'Author One' },
      { id: 2, name: 'Author Two' },
      { id: 3, name: 'Author Three' },
    ];
  }

  fetchBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (res) => this.books = res,
      error: (err) => console.error('Failed to load books', err)
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.selectedFileName = this.selectedFile.name;
    }
  }

  onSubmit(): void {
    if (this.bookForm.invalid || !this.selectedFile) return;

    const formValue = this.bookForm.value;
    const addBookDto = {
      bookName: formValue.bookName,
      bookIsbnNumber: formValue.bookIsbnNumber,
      bookPrice: formValue.bookPrice,
      bookRating: formValue.bookRating,
      bookCategory: formValue.bookCategory,
      bookQuantity: formValue.bookQuantity,
      bookAuthorIds: formValue.bookAuthorIds,
    };

    const formData = new FormData();
    formData.append('book', new Blob([JSON.stringify(addBookDto)], { type: 'application/json' }));
    formData.append('image', this.selectedFile);

    this.bookService.addBook(formData).subscribe({
      next: (res) => {
        this.books.push(res);
        this.onModalClose();
      },
      error: (err) => alert('Failed to add book.')
    });
  }

  onModalClose(): void {
    this.bookForm.reset();
    this.selectedFile = null;
    this.selectedFileName = '';
    const modalEl = document.getElementById('addBookModal');
    if (modalEl) bootstrap.Modal.getInstance(modalEl)?.hide();
  }

  onEdit(book: any): void {
    console.log('Edit', book);
  }

  onDelete(book: any): void {
    if (confirm(`Delete "${book.bookName}"?`)) {
      this.bookService.deleteBook(book.id).subscribe({
        next: () => this.books = this.books.filter(b => b.id !== book.id),
        error: (err) => alert('Failed to delete book')
      });
    }
  }
}
