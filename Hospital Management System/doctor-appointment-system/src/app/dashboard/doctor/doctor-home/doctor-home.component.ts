import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../service/doctor.service';
import { Modal } from 'bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-doctor-home',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './doctor-home.component.html',
  styleUrl: './doctor-home.component.css'
})
export class DoctorHomeComponent implements OnInit {
  patients: any[] = [];
  selectedPatient: any = null;
  doctorId: number = 0; // You can fetch this from login or token storage

  constructor(private doctorService: DoctorService) {}

  ngOnInit(): void {
    // For demo, hardcoded doctor ID; replace with auth-based doctor ID
    this.doctorId = Number(localStorage.getItem('doctorId') || 1);
    this.loadPatients();
  }

  loadPatients(): void {
    this.doctorService.getAdmittedPatientsByDoctor(this.doctorId).subscribe({
      next: (res) => (this.patients = res),
      error: (err) => console.error('Failed to load patients', err),
    });
  }

  viewPatientDetails(patient: any): void {
    this.selectedPatient = patient;
    const modalEl = document.getElementById('patientDetailsModal');
    if (modalEl) {
      const modal = new Modal(modalEl);
      modal.show();
    }
  }
}
