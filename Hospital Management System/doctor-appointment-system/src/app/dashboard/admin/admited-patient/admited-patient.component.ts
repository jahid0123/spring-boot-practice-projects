import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-admited-patient',
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './admited-patient.component.html',
  styleUrl: './admited-patient.component.css'
})
export class AdmittedPatientComponent implements OnInit {
 
  patients: any[] = [];
  selectedPatient: any = null;

  advanceAmount: number = 0;
  advancePatientId: number | null = null;

  bill: any = null;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(): void {
    this.adminService.getAllAdmittedPatients().subscribe({
      next: (res) => this.patients = res,
      error: (err) => console.error('Failed to load patients:', err)
    });
  }

  openDetails(patient: any): void {
    this.selectedPatient = patient;
  }

  openAdvanceModal(patient: any): void {
    this.advancePatientId = patient.id;
    this.advanceAmount = 0;

    const modalEl = document.getElementById('advanceModal');
    if (modalEl) {
      const modal = new Modal(modalEl);
      modal.show();
    }
  }

  submitAdvance(): void {
    if (this.advancePatientId == null || this.advanceAmount <= 0) {
      alert('Please enter a valid amount');
      return;
    }

    this.adminService.addAdvancePayment(this.advancePatientId, this.advanceAmount).subscribe({
      next: () => {
        alert('Advance added successfully');
        this.loadPatients();
        const modalEl = document.getElementById('advanceModal');
        if (modalEl) Modal.getInstance(modalEl)?.hide();
      },
      error: err => {
        console.error(err);
        alert('Failed to add advance');
      }
    });
  }

  discharge(patientId: number): void {
    const patient = this.patients.find(p => p.id === patientId);
    if (!patient) {
      alert('Patient not found');
      return;
    }

    if (patient.dueAmount > 0) {
      alert('Patient cannot be discharged. Advance amount is less than total.');
      return;
    }

    if (confirm('Are you sure you want to discharge this patient?')) {
      this.adminService.dischargePatient(patientId).subscribe({
        next: () => {
          alert('Patient discharged');
          this.loadPatients();
        },
        error: err => {
          console.error(err);
          alert('Discharge failed');
        }
      });
    }
  }

  downloadReceipt(patientId: number): void {
  this.adminService.downloadReceipt(patientId).subscribe({
    next: (blob) => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = `receipt_${patientId}.pdf`;
      a.click();
      window.URL.revokeObjectURL(url);
    },
    error: err => {
      console.error(err);
      alert('Failed to download receipt');
    }
  });
}


  showBill(patientId: number): void {
    this.adminService.getBill(patientId).subscribe({
      next: (res) => {
        this.bill = res;
        const modalEl = document.getElementById('billModal');
        if (modalEl) new Modal(modalEl).show();
      },
      error: err => {
        console.error(err);
        alert('Failed to load bill');
      }
    });
  }
}