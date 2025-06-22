import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../user/user-dashboard/service/user.service';
import { Router } from '@angular/router';
import { User } from '../../../../model/user';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-list',
  imports: [CommonModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUser().subscribe({
      next: (data: User[]) => {
        this.users = data;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Failed to load users:', err.message);
      }
    });
  }

  deleteUser(id: number): void {
    if (confirm('Are you sure you want to delete this user?')) {
      this.userService.deleteUser(id).subscribe({
        next: () => {
          console.log('User deleted successfully');
          this.loadUsers(); // refresh the list
        },
        error: (err: HttpErrorResponse) => {
          console.error('Failed to delete user:', err.message);
        }
      });
    }
  }
}