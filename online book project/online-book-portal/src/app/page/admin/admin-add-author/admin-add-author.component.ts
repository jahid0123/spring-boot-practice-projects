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

  // Tracks whether we're editing or adding
  editingAuthor: boolean = false;
  editingIndex:  number | null = null;

  constructor(
    private addAuthorService: AddAuthorService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadAuthors();
  }
getAuthorNamesByIds(ids: string): string {
  if (!ids) return '';
  const idArray = ids.split(',').map(id => id.trim());
  const names = idArray.map(id => {
    const author = this.authors.find((a: any) => a.authorId == id);
    return author ? author.authorName : `Unknown (${id})`;
  });
  return names.join(', ');
}
  // Load all authors from server
  loadAuthors(): void {
    this.addAuthorService.getAllAuthors().subscribe({
      next: (data) => (this.getAuthors = data),
      error: (err) => console.error('Failed to load authors:', err)
    });
  }

  // Called when user clicks "Add Author"
  openAddAuthorModal(): void {
    this.author = new AddAuthor();
    this.editingAuthor = false;
    this.openModal();
  }

  // Called when user clicks "Edit" button on an author
  openEditAuthorModal(author: Author): void {
    this.author = new AddAuthor(author); // Copy fields
    this.editingAuthor = true;
    this.openModal();
  }

  // Submit form (add or update based on flag)
  

  onSubmitAuthor(): void {
    if (this.editingAuthor&&this.editingIndex!==null) {
      
      this.addAuthorService.editAuthor(this.author).subscribe({
        next: (updatedauthor) => {
          console.log('Author updated successfully');
          this.closeAuthorModal();
          this.loadAuthors();
        },
        error: (err) => console.error('Failed to update author:', err)
      });
    } else {
      // Add new author
      this.addAuthorService.addAuthor(this.author).subscribe({
        next: () => {
          console.log('Author added successfully');
          this.closeAuthorModal();
          this.loadAuthors();
        },
        error: (err) => console.error('Failed to add author:', err)
      });
    }
  }

  // Delete author by ID
  deleteAuthor(id: number): void {
    if (confirm('Are you sure you want to delete this author?')) {
      this.addAuthorService.deleteAuthor(id).subscribe({
        next: () => {
          console.log('Author deleted successfully');
          this.loadAuthors();
        },
        error: (err) => console.error('Failed to delete author:', err)
      });
    }
  }

  // Open Bootstrap modal
  openModal(): void {
    const modal = new Modal(document.getElementById('authorModal')!);
    modal.show();
  }

  // Close Bootstrap modal
  closeAuthorModal(): void {
    const modalElement = document.getElementById('authorModal');
    const modalInstance = Modal.getInstance(modalElement!);
    modalInstance?.hide();
  }

  // Dynamic label for the submit button
  get buttonLabel(): string {
    return this.editingAuthor ? 'Update' : 'Add';
  }
}
