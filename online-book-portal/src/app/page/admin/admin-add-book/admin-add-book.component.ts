import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import Modal from 'bootstrap/js/dist/modal';
import { AddBook } from '../../../model/class';
import { AddBookService } from './service/add-book.service';

@Component({
  selector: 'app-admin-add-book',
  imports: [FormsModule, NgFor],
  templateUrl: './admin-add-book.component.html',
  styleUrl: './admin-add-book.component.css'
})
export class AdminAddBookComponent {


  addBook: AddBook | undefined;

  constructor(private addBookService: AddBookService, private router: R){}

onFileSelected($event: Event) {
throw new Error('Method not implemented.');
}
 books: any[] = [];
  book: any = {};
  editingIndex: number | null = null;

  get editingBook() {
    return this.editingIndex !== null;
  }

  openAddModal() {
    this.book = {};
    this.editingIndex = null;
    this.openModal();
  }

  openEditModal(index: number) {
    this.book = { ...this.books[index] };
    this.editingIndex = index;
    this.openModal();
  }

  deleteBook(index: number) {
    this.books.splice(index, 1);
  }

  onSubmit() {
    if (this.editingIndex !== null) {
      this.books[this.editingIndex] = { ...this.book };
    } else {
      this.books.push({ ...this.book });
    }

    this.closeModal();
  }

  openModal() {
    const modal = new Modal(document.getElementById('bookModal')!);
    modal.show();
  }

  closeModal() {
    const modalElement = document.getElementById('bookModal');
    const modalInstance = Modal.getInstance(modalElement!);
    modalInstance?.hide();
  }
}
