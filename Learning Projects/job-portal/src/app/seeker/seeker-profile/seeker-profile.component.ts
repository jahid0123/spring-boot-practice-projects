import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SeekerService } from '../service/seeker.service';
import Modal from 'bootstrap/js/dist/modal';

@Component({
  selector: 'app-seeker-profile',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './seeker-profile.component.html',
  styleUrl: './seeker-profile.component.css',
})
export class SeekerProfileComponent implements OnInit {
 user: any;
  editForm!: FormGroup;
  modalInstance: any;

  constructor(private fb: FormBuilder, private seekerService: SeekerService) {}

  ngOnInit(): void {
    this.editForm = this.fb.group({
      id: [0],
      name: ['', Validators.required],
      education: [''],
      jobExperience: [''],
      designation: [''],
      phone: [''],
      address: ['']
    });

    this.loadProfile();
  }

  loadProfile(): void {
    this.seekerService.getMyProfileInfo().subscribe({
      next: (data: any) => {
        this.user = data;
      },
      error: (err) => {
        console.error('Failed to load seeker profile:', err);
      }
    });
  }

  openEditModal(): void {
    if (this.user) {
      this.editForm.patchValue({
        id: this.user.id,
        name: this.user.name,
        education: this.user.education,
        jobExperience: this.user.jobExperience,
        designation: this.user.designation,
        phone: this.user.phone,
        address: this.user.address
      });

      const modalElement = document.getElementById('editModal');
      if (modalElement) {
        this.modalInstance = new Modal(modalElement);
        this.modalInstance.show();
      }
    }
  }

  saveChanges(): void {
    if (this.editForm.valid) {
      this.seekerService.editProfileInfo(this.editForm.value).subscribe({
        next: (updated: any) => {
          this.user = updated;
          this.modalInstance.hide();
          alert('Profile updated successfully!');
          this.loadProfile();
        },
        error: (err) => {
          console.error('Update failed:', err);
          alert('Failed to update profile!');
        }
      });
    }
  }
}
