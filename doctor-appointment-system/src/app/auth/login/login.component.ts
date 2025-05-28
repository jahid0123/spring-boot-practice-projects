import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {


   email = '';
  password = '';

  constructor(private auth: AuthService) {}

  onLogin() {
    this.auth.login({ email: this.email, password: this.password }).subscribe();
  }
}
