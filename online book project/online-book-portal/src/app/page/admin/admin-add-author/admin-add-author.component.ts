import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../user/user-dashboard/service/user.service';
import { AddAuthorService } from './service/add-author.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AddAuthor, Author } from '../../../model/class';
import { TrackByFunction } from '@angular/core';
import Modal from 'bootstrap/js/dist/modal';


@Component({
  selector: 'app-admin-add-author',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './admin-add-author.component.html',
  styleUrl: './admin-add-author.component.css'
})
export class AdminAddAuthorComponent {
  author: AddAuthor = new AddAuthor();
  authors: Author[] = [];
  getAuthors: Author[] = [];

  editingAuthor: boolean = false;

  constructor(
    private addAuthorService: AddAuthorService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadAuthors();
  }

  loadAuthors(): void {
    this.addAuthorService.getAllAuthors().subscribe({
      next: (data) => (this.getAuthors = data),
      error: (err) => console.error('Failed to load authors:', err),
    });
  }

  openAddAuthorModal(): void {
    this.author = new AddAuthor();
    this.editingAuthor = false;
    this.openModal();
  }

  openEditAuthorModal(a: Author): void {
    this.author = { ...a }; // shallow copy to avoid two-way binding issues
    this.editingAuthor = true;
    this.openModal();
  }

  onSubmitAuthor(): void {
    if (this.editingAuthor) {
      this.addAuthorService.editAuthor(this.author).subscribe({
        next: () => {
          console.log('Author updated successfully');
          this.closeAuthorModal();
          this.loadAuthors();
        },
        error: (err) => console.error('Failed to update author:', err),
      });
    } else {
      this.addAuthorService.addAuthor(this.author).subscribe({
        next: () => {
          console.log('Author added successfully');
          this.closeAuthorModal();
          this.loadAuthors();
        },
        error: (err) => console.error('Failed to add author:', err),
      });
    }
  }

  deleteAuthor(id: number): void {
    if (confirm('Are you sure you want to delete this author?')) {
      this.addAuthorService.deleteAuthor(id).subscribe({
        next: () => {
          console.log('Author deleted successfully');
          this.loadAuthors();
        },
        error: (err) => console.error('Failed to delete author:', err),
      });
    }
  }

  openModal(): void {
    const modal = new Modal(document.getElementById('authorModal')!);
    modal.show();
  }

  closeAuthorModal(): void {
    const modalElement = document.getElementById('authorModal');
    const modalInstance = Modal.getInstance(modalElement!);
    modalInstance?.hide();
  }

  trackByAuthorId(index: number, item: Author): number {
    return item.id;
  }
}