import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from './service/login.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value).subscribe({
        next: (response) => {
          // Store response fields in localStorage
          localStorage.setItem('token', response.token);
          localStorage.setItem('id', response.id);
          localStorage.setItem('role', response.role);

          this.successMessage = 'Login successful!';
          this.errorMessage = null;
          this.loginForm.reset();

          // Redirect to dashboard (optional, adjust path as needed)
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          this.errorMessage = err.message || 'Login failed. Please check your credentials.';
          this.successMessage = null;
        }
      });
    }
  }
}
