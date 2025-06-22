import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AddBookService } from './service/add-book.service';
import { CommonModule, NgFor } from '@angular/common';
import { Modal } from 'bootstrap';


@Component({
  selector: 'app-admin-add-book',
  imports: [ ReactiveFormsModule, CommonModule],
  templateUrl: './admin-add-book.component.html',
  styleUrl: './admin-add-book.component.css',
})
export class AdminAddBookComponent implements OnInit {
  @ViewChild('addBookModal', { static: false }) addBookModal!: ElementRef;

  books: any[] = [];
  allAuthors: any[] = [];
  bookForm!: FormGroup;
  isEditMode: boolean = false;
  selectedBook: any = null;
  selectedFile: File | null = null;
  previewUrls: string[] = [];
  modalInstance: Modal | null = null;

  constructor(private fb: FormBuilder, private bookService: AddBookService) {}

  ngOnInit(): void {
    this.initializeForm();
    this.loadBooks();
    this.loadAuthors();
  }

  initializeForm(): void {
    this.bookForm = this.fb.group({
      bookName: ['', Validators.required],
      bookIsbnNumber: ['', Validators.required],
      bookPrice: ['', [Validators.required, Validators.min(0)]],
      bookRating: ['', [Validators.required, Validators.min(0), Validators.max(5)]],
      bookCategory: ['', Validators.required],
      bookDescription: ['', Validators.required],
      bookQuantity: ['', [Validators.required, Validators.min(0)]],
      bookAuthorIds: [[], Validators.required]
    });
  }

  loadBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (data) => {
        this.books = data.map((book: any) => ({
          ...book,
          bookImageUrl: book.bookImageUrl
            ? `http://localhost:8082/api/auth/image/${book.bookImageUrl}`
            : '12.jpg'
        }));
      },
      error: (err) => console.error('Error loading books:', err)
    });
  }

  loadAuthors(): void {
    this.bookService.getAllAuthors().subscribe({
      next: (res) => this.allAuthors = res,
      error: (err) => console.error('Error loading authors:', err)
    });
  }

  onAdd(): void {
    this.clearForm();
    this.isEditMode = false;
  }

  onEdit(book: any): void {
    this.isEditMode = true;
    this.selectedBook = book;
    this.bookForm.patchValue({
      ...book,
      bookAuthorIds: book.bookAuthorIds || []
    });
    this.previewUrls = book.bookImageUrl ? [book.bookImageUrl] : [];
  }

  onSubmit(): void {
  if (this.bookForm.invalid) return;

  const formData = new FormData();
  const bookData = this.bookForm.value;

  formData.append('book', new Blob([JSON.stringify(bookData)], { type: 'application/json' }));
  if (this.selectedFile) {
    formData.append('image', this.selectedFile);
  }

  if (this.isEditMode && this.selectedBook) {
    this.bookService.updateBook(formData).subscribe({
      next: () => {
        this.loadBooks();
        this.closeModal();
      },
      error: (err) => console.error('Error updating book:', err)
    });
  } else {
    this.bookService.addBook(formData).subscribe({
      next: () => {
        this.loadBooks();
        this.closeModal();
      },
      error: (err) => console.error('Error adding book:', err)
    });
  }
}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      this.selectedFile = input.files[0];

      const reader = new FileReader();
      reader.onload = () => this.previewUrls = [reader.result as string];
      reader.readAsDataURL(this.selectedFile);
    }
  }

  onImageError(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = '12jpg';
  }

  removeSelectedImage(index: number): void {
    this.previewUrls.splice(index, 1);
    if (this.previewUrls.length === 0) {
      this.selectedFile = null;
    }
  }

  onDelete(bookId: number): void {
    if (confirm('Are you sure you want to delete this book?')) {
      this.bookService.deleteBook(bookId).subscribe({
        next: () => this.loadBooks(),
        error: (err) => console.error('Error deleting book:', err)
      });
    }
  }

  clearForm(): void {
    this.bookForm.reset();
    this.selectedBook = null;
    this.selectedFile = null;
    this.previewUrls = [];
    this.isEditMode = false;
  }

  openModal(): void {
    if (this.addBookModal) {
      this.modalInstance = new Modal(this.addBookModal.nativeElement);
      this.modalInstance.show();
    }
  }

  closeModal(): void {
    if (this.modalInstance) {
      this.modalInstance.hide();
    }
  }
}