import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserRegistration } from '../../../model class/user';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buyer-register',
  imports: [FormsModule, ReactiveFormsModule, CommonModule, NgIf],
  templateUrl: './buyer-register.component.html',
  styleUrl: './buyer-register.component.css',
})
export class BuyerRegisterComponent {
  userData: UserRegistration = {
    name: '',
    email: '',
    password: '', // Initialize password field
    phone: '',
  };

  submitted = false;

  submittedData: UserRegistration = {
    name: '',
    email: '',
    password: '',
    phone: '',
  };

  constructor(private authService: AuthService, private router: Router) {} // Constructor for dependency injection (not needed for this simple example)

  onSubmit(): void {
    // Check if the form is valid before processing (though the button is disabled if invalid)
    if (
      this.userData.name &&
      this.userData.email &&
      this.userData.password &&
      this.userData.phone
    ) {
      this.authService.buyerRegister(this.userData).subscribe({
        next: (res) => {
          console.log('Form Submitted!', this.userData);
          alert('Buyer Register Successfully');
          this.router.navigateByUrl('/login');
        },
        error: (err) => {
          alert('Ops..., Registration Failled!!');
        },
      });

      this.submittedData = {
        name: this.userData.name,
        email: this.userData.email,
        password: this.userData.password,
        phone: this.userData.phone,
      };

      this.submitted = true; // Set flag to true to display submitted data
    } else {
      console.warn('Form is invalid or incomplete. Please check all fields.');
    }
  }
}
