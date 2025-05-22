import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe({
        next: (response) => {
          this.successMessage = response.message || 'Registration successful!';
          this.errorMessage = null;
          this.registerForm.reset();
        },
        error: (err) => {
          this.errorMessage = err.message || 'Registration failed. Please try again.';
          this.successMessage = null;
        }
      });
    }
  }

}
