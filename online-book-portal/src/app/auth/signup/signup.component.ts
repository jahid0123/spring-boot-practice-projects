import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-signup',
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent implements OnInit {
  fullName: string = '';
  email: string = '';
  password: string = '';

  constructor(
    private router: Router,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {}

  onSignup(): void {
    const signupForm = {
      name: this.fullName,
      email: this.email,
      password: this.password,
    };

    this.authService.register(signupForm).subscribe({
      next: () => {
        alert('Registration successful');
        this.router.navigateByUrl('/login');
      },
      error: () => {
        alert('Registration failed');
      },
    });
  }
}
