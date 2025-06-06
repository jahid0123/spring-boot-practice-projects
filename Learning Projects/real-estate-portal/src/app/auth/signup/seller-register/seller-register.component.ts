import { Component } from '@angular/core';
import { UserRegistration } from '../../../model class/user';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-seller-register',
  imports: [FormsModule, NgIf],
  templateUrl: './seller-register.component.html',
  styleUrl: './seller-register.component.css',
})
export class SellerRegisterComponent {
  // userData holds the form input values. It's initialized with empty strings.
  userData: UserRegistration = {
    name: '',
    email: '',
    password: '', // Initialize password field
    phone: '',
  };

  // submitted is a flag to show/hide the submitted data section in the template
  submitted = false;

  // submittedData will store a copy of the userData after successful submission
  // Note: In a real application, you would typically send this data to a backend service.
  submittedData: UserRegistration = {
    name: '',
    email: '',
    password: '',
    phone: '',
  };

  constructor(private authService: AuthService, private router: Router) {} // Constructor for dependency injection (not needed for this simple example)

  /**
   * Handles the form submission logic.
   * This method is called when the form's (ngSubmit) event is triggered.
   */
  onSubmit(): void {
    // Check if the form is valid before processing (though the button is disabled if invalid)
    if (
      this.userData.name &&
      this.userData.email &&
      this.userData.password &&
      this.userData.phone
    ) {
      this.authService.sellerRegister(this.userData).subscribe({
        next: (res) => {
          console.log('Form Submitted!', this.userData);
          alert('Seller Register Successfully');
          this.router.navigateByUrl('/login');
        },
        error: (err) => {
          alert('Ops..., Registration Failled!!');
        },
      });

      // For demonstration, we'll store the submitted data (excluding password for display)
      this.submittedData = {
        name: this.userData.name,
        email: this.userData.email,
        password: this.userData.password,
        phone: this.userData.phone,
      };

      this.submitted = true; // Set flag to true to display submitted data

      // Optionally, reset the form after submission
      // this.userData = { name: '', email: '', password: '', phone: '' };
      // this.registrationForm.resetForm(); // If you pass the form instance to onSubmit
    } else {
      console.warn('Form is invalid or incomplete. Please check all fields.');
    }
  }
}
