import { Component } from '@angular/core';
import { UserService } from '../../../user/user-dashboard/service/user.service';
import { Router } from '@angular/router';
import { User } from '../../../../model/user';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-list',
  imports: [CommonModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {
  users: User[] = [];
  editingUser: boolean = false;
  editingIndex: number | null = null;
  user: User = new User(); // Make sure your User model has a default constructor or fields are initialized

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUser().subscribe((data) => {
      this.users = data;
    });
  }

  onSubmit(): void {
    if (this.editingUser && this.editingIndex !== null) {
      this.userService.updateUser(this.user).subscribe({
        next: () => {
          console.log('User updated successfully');
          this.loadUsers();
          this.closeModal();
        },
        error: (err) => {
          console.error('Failed to update user:', err);
        }
      });
    } else {
      alert('Invalid edit state.');
    }
  }

  deleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe({
      next: () => {
        console.log('User deleted successfully');
        this.loadUsers(); // refresh list
      },
      error: (err) => {
        console.error('Failed to delete user:', err);
      }
    });
  }

  updateUser(user: User): void {
    this.editingUser = true;
    this.editingIndex = user.id;
    this.user = { ...user }; // clone the object
  }

  closeModal(): void {
    this.editingUser = false;
    this.editingIndex = null;
    this.user = new User();
  }
}