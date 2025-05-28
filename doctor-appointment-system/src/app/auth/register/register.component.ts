import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {


   name = '';
  email = '';
  password = '';
  role = 'PATIENT';

  constructor(private auth: AuthService) {}

  onRegister() {
    this.auth.register({ name: this.name, email: this.email, password: this.password, role: this.role }).subscribe();
  }
}
