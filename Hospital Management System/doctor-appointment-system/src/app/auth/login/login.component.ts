import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule, NgClass, NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [RouterModule, ReactiveFormsModule, NgIf, NgClass, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm: FormGroup;
  submitted = false;
  showPassword = false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private fb: FormBuilder,
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  onLogin() {
    this.submitted = true;

    if (this.loginForm.invalid) return;

    const loginData = {
      email: this.f['email'].value,
      password: this.f['password'].value,
    };

    this.auth.login(loginData).subscribe({
      next: (res) => {
        console.log('Login successful:', res);
        this.auth.redirectByRole(res.role); // Now works properly
      },
      error: (err) => {
        console.error('Login failed:', err);
        alert('Username or password is invalid!');
      },
    });
  }
}
