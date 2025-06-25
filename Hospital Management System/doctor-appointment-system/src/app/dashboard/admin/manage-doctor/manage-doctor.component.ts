import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { ManageUsersService } from '../manage-users/service/manage-users.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-manage-doctor',
  imports: [NgIf, NgFor, CommonModule],
  templateUrl: './manage-doctor.component.html',
  styleUrl: './manage-doctor.component.css'
})
export class ManageDoctorComponent  implements OnInit {
  doctors: any[] = [];
  selectedDoctor: any = null;

  constructor(private doctorService: ManageUsersService) {}

  ngOnInit(): void {
    this.loadDoctors();
  }

  loadDoctors(): void {
    this.doctorService.getAllDoctors().subscribe({
      next: (res) => {
        this.doctors = res;
      },
      error: (err) => {
        console.error('Error loading doctors', err);
      }
    });
  }

  viewDoctorDetails(doctor: any): void {
    this.selectedDoctor = doctor;
  }
}