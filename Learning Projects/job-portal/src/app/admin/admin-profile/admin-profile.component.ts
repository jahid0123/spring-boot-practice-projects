import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { AdminProfile, EditAdminProfile } from '../../model/model';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-admin-profile',
  imports: [FormsModule, NgIf, CommonModule],
  templateUrl: './admin-profile.component.html',
  styleUrl: './admin-profile.component.css'
})
export class AdminProfileComponent implements OnInit {

  admin: AdminProfile | undefined;
  editedAdmin: EditAdminProfile = { userId: 0, name: '', phone: '' };
  isEditing = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(): void {
    this.adminService.getProfileInfo().subscribe({
      next: (data: AdminProfile) => {
        this.admin = data;
      },
      error: (err) => {
        console.error('Failed to load admin profile:', err);
      }
    });
  }

  openEditModal(): void {
    if (this.admin) {
      this.editedAdmin = {
        userId: this.admin.id,
        name: this.admin.name,
        phone: this.admin.phone
      };
      this.isEditing = true;
    }
  }

  saveChanges(): void {
    if (!this.editedAdmin) return;

    this.adminService.editUserInfo(this.editedAdmin).subscribe({
      next: (updated: AdminProfile) => {
        this.admin = updated;
        this.isEditing = false;
        alert('Profile updated successfully!');
        this.loadProfile();
      },
      error: (err) => {
        console.error('Update failed:', err);
        alert('Failed to update profile!');
      }
    });
  }

  cancelEdit(): void {
    this.isEditing = false;
  }
}