import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-company',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register-company.component.html',
  styleUrl: './register-company.component.css'
})
export class RegisterCompanyComponent {

 registrationForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      phone: ['', Validators.required],
      business: [''],
      address: ['']
    });
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      this.authService.companyRegister(this.registrationForm.value).subscribe({
        next: res=>{
          console.log(res);
          alert('Job Company Register Successfully..');
          this.router.navigateByUrl('/login');
        },
        error: err=>{
          console.error(err);
          alert('Ops, Registration Failed!!!');
        }
      });
    } else {
      console.log('Form is invalid');
    }
  }
 
}
