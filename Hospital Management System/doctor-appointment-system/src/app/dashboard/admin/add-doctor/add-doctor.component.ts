import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AddDoctoerService } from './service/add-doctoer.service';
import { Router } from '@angular/router';
import { DoctorRegisterRequestDto } from '../../../model/model.classes';

@Component({
  selector: 'app-add-doctor',
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './add-doctor.component.html',
  styleUrl: './add-doctor.component.css'
})
export class AddDoctorComponent {

  doctorForm: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder, private addService: AddDoctoerService, private router: Router) {
    this.doctorForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      specialization: ['', Validators.required],
      qualification: ['', Validators.required],
      experience: ['', [Validators.required, Validators.min(0)]],
      hospitalName: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('[0-9]{11}')]],
    });
  }

  get f() {
    return this.doctorForm.controls;
  }



  onSubmit() {
    this.submitted = true;
    if (this.doctorForm.invalid) return;

    const doctorData: DoctorRegisterRequestDto = this.doctorForm.value;

    this.addService.addDoctor(doctorData).subscribe({
      next: (res) => {
        console.log('Doctoer add successfully.');
        this.doctorForm.reset();
        this.router.navigateByUrl('/admin');
      }, 
      error: (err) => {
        alert('Doctor registration failed!!!');

      }
    });
    // Send to backend
    console.log('Doctor Registration Data:', this.doctorForm.value);
    // Example: this.doctorService.registerDoctor(this.doctorForm.value).subscribe()
  }

}
