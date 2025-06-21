import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SeekerService } from '../service/seeker.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-seeker-settings',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './seeker-settings.component.html',
  styleUrl: './seeker-settings.component.css'
})
export class SeekerSettingsComponent implements OnInit {
  passwordForm!: FormGroup;

  constructor(private fb: FormBuilder, private seekerService: SeekerService) {}

  ngOnInit(): void {
    this.passwordForm = this.fb.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    const newPassword = form.get('newPassword')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;
    return newPassword === confirmPassword ? null : { passwordMismatch: true };
  }

  changePassword(): void {
    if (this.passwordForm.valid) {
      const currentPassword = this.passwordForm.get('currentPassword')?.value;
      const newPassword = this.passwordForm.get('newPassword')?.value;
      const id = Number(localStorage.getItem('id'));

      this.seekerService.changePassword({ id, currentPassword, newPassword }).subscribe({
        next: res => {
          console.log('Password Change Response:', res);
          alert('Password changed successfully!');
          this.passwordForm.reset();
        },
        error: err => {
          console.error('Password change failed:', err);
          alert('Password change failed!');
        }
      });
    }
  }
}