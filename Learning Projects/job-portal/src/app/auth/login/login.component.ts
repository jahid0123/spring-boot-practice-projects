import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  imports: [RouterModule, ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

    loginData = {
    email: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router){}

  onLogin() {

    this.authService.login(this.loginData).subscribe({
      next: (res) => {
        alert('Login successfully...');
        this.authService.redirectByRole(res.role); // Now works properly

      },
      error: (err) => {
        console.error('Login failed:', err);
        alert('Username or password is invalid!');
      },
    });
  }

}
