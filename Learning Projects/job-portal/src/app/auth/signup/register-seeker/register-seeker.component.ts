import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register-seeker',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register-seeker.component.html',
  styleUrl: './register-seeker.component.css'
})
export class RegisterSeekerComponent {

   registrationForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      phone: ['', Validators.required],
      education: [''],
      jobExperience: [''],
      designation: [''],
      address: ['']
    });
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      this.authService.seekerRegister(this.registrationForm.value).subscribe({
        next: res=>{
          console.log(res);
          alert('Job Seeker Register Successfully..');
          this.router.navigateByUrl('/login');
        },
        error: err=>{
          console.error(err);
          alert('Ops, Registration Failed!!!');
        }
      });
      // Here you can call a service to send data to backend
    } else {
      console.log('Form is invalid');
    }
  }
}
