import { Component, OnInit } from '@angular/core';
import { UserService } from '../user-dashboard/service/user.service';
import { CommonModule } from '@angular/common';
import { Modal } from 'bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
  user: any;
  editUser: any = {};
  error: string | null = null;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.fetchUserDetails();
  }

  fetchUserDetails(): void {
    this.userService.getUserInfo().subscribe({
      next: (response) => {
        this.user = response;
      },
      error: (err) => {
        this.error = 'Failed to load user data.';
        console.error(err);
      }
    });
  }

  openEditModal(): void {
    this.editUser = { ...this.user };
  }

  onUpdateProfile(): void {
    const updatePayload = {
      name: this.editUser.name,
      phoneNo: this.editUser.phoneNumber,
      address: this.editUser.address
    };

    this.userService.editUserInfo(updatePayload).subscribe({
      next: () => {
        this.user = { ...this.user, ...updatePayload };

        // Hide the modal manually
        const modalEl = document.getElementById('editProfileModal');
        if (modalEl) {
          const modalInstance = Modal.getInstance(modalEl);
          modalInstance?.hide();
        }
      },
      error: (err) => {
        this.error = 'Failed to update user data.';
        console.error(err);
      }
    });
  }
}