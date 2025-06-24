import { Component, OnInit } from '@angular/core';
import { Modal } from 'bootstrap';
import { UserService } from '../../user/user-dashboard/service/user.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-profile',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './admin-profile.component.html',
  styleUrl: './admin-profile.component.css'
})
export class AdminProfileComponent implements OnInit {
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