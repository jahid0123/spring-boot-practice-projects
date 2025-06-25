import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminService } from '../service/admin.service';
import { ManageUsersService } from '../manage-users/service/manage-users.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admission-patient',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './admission-patient.component.html',
  styleUrl: './admission-patient.component.css'
})
export class AdmissionPatientComponent implements OnInit {

  admitForm!: FormGroup;
  beds: any[] = [];
  doctors: any[] = [];

  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private doctorService: ManageUsersService
  ) {}

  ngOnInit(): void {
    this.admitForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      gender: ['', Validators.required],
      dob: ['', Validators.required],
      address: ['', Validators.required],
      doctorId: ['', Validators.required],
      bedId: ['', Validators.required],
      advanceAmount: [0, [Validators.required, Validators.min(0)]]
    });

    this.loadDoctors();
    this.loadBeds();
  }

  loadDoctors(): void {
    this.doctorService.getAllDoctors().subscribe({
      next: (res) => this.doctors = res,
      error: (err) => console.error(err)
    });
  }

  loadBeds(): void {
    this.adminService.getAllBed().subscribe({
      next: (res) => this.beds = res.filter((b: any) => !b.isOccupied),
      error: (err) => console.error(err)
    });
  }

  onSubmit(): void {
    if (this.admitForm.invalid) return;

    this.adminService.admitPatient(this.admitForm.value).subscribe({
      next: () => {
        alert('Patient admitted successfully');
        this.admitForm.reset({ advanceAmount: 0 });
        this.loadBeds(); // refresh bed list
      },
      error: (err) => console.error(err)
    });
  }
}