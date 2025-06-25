import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-discharge-patient',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './discharge-patient.component.html',
  styleUrl: './discharge-patient.component.css'
})
export class DischargePatientComponent implements OnInit {
  admittedPatients: any[] = [];
  dischargedPatients: any[] = [];
  selectedPatient: any = null;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadAdmittedPatients();
    this.loadDischargedPatients();
  }

  loadAdmittedPatients(): void {
    this.adminService.getAllAdmittedPatients().subscribe({
      next: res => this.admittedPatients = res,
      error: err => console.error('Failed to load admitted patients', err)
    });
  }

  loadDischargedPatients(): void {
    this.adminService.getDischargedPatients().subscribe({
      next: res => this.dischargedPatients = res,
      error: err => console.error('Failed to load discharged patients', err)
    });
  }

  openDetails(patient: any): void {
    this.selectedPatient = patient;
    // You can implement modal show here if needed
  }
}