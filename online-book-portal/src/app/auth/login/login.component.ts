import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {
    
  }

  onLogin(): void {
    const loginForm ={
      email: this.email,
      password: this.password
    }
      this.authService.login(loginForm).subscribe({
        next: (res: any) => {
          localStorage.setItem('token', res.token);
          localStorage.setItem('id', res.id);
          localStorage.setItem('role', res.role);

          alert('Login successful');

          if (res.role === 'USER') {
            this.router.navigate(['/user-dashboard']);
          } else if (res.role === 'ADMIN') {
            this.router.navigate(['/admin-dashboard']);
          }
        },
        error: () => {
          alert('Login failed');
        },
      });
    
  }

}
