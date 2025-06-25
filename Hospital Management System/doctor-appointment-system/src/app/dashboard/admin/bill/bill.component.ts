import { Component, OnInit } from '@angular/core';
import { Modal } from 'bootstrap';
import { AdminService } from '../service/admin.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-bill',
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './bill.component.html',
  styleUrl: './bill.component.css'
})
export class BillComponent implements OnInit {
  patients: any[] = [];
  selectedPatient: any = null;
  advanceAmount: number = 0;
  advancePatientId: number | null = null;

  // Bill creation properties
  doctorFee: number = 0;
  medicineCost: number = 0;

  // To hold fetched bill details for display
  bill: any = null;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(): void {
    this.adminService.getAllAdmittedPatients().subscribe({
      next: (res: any) => (this.patients = res),
      error: (err) => console.error('Failed to load patients:', err),
    });
  }

  openDetails(patient: any): void {
    this.selectedPatient = patient;
  }

  openAdvanceModal(patient: any): void {
    this.advancePatientId = patient.id;
    this.advanceAmount = 0;
    this.showModalById('advanceModal');
  }

  submitAdvance(): void {
    if (this.advancePatientId == null || this.advanceAmount <= 0) {
      alert('Please enter a valid advance amount.');
      return;
    }
    this.adminService.addAdvancePayment(this.advancePatientId, this.advanceAmount).subscribe({
      next: () => {
        alert('Advance payment added successfully');
        this.loadPatients();
        this.hideModalById('advanceModal');
      },
      error: (err) => {
        console.error(err);
        alert('Failed to add advance payment');
      },
    });
  }

  discharge(patientId: number): void {
    const patient = this.patients.find((p) => p.id === patientId);
    if (!patient) {
      alert('Patient not found');
      return;
    }
    if (patient.advanceAmount < patient.dueAmount) {
      alert('Patient cannot be discharged. Advance amount is less than due amount.');
      return;
    }

    if (confirm('Are you sure you want to discharge this patient?')) {
      this.adminService.dischargePatient(patientId).subscribe({
        next: () => {
          alert('Patient discharged successfully');
          this.loadPatients();
        },
        error: (err) => {
          console.error(err);
          alert('Error occurred while discharging patient');
        },
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
      error: (err) => {
        console.error(err);
        alert('Failed to download receipt');
      },
    });
  }

  // Bill Creation Modal
  openBillCreateModal(patient: any): void {
    this.selectedPatient = patient;
    this.doctorFee = 0;
    this.medicineCost = 0;
    this.showModalById('billCreateModal');
  }

  submitBill(): void {
    if (!this.selectedPatient) {
      alert('No patient selected');
      return;
    }
    if (this.doctorFee < 0 || this.medicineCost < 0) {
      alert('Please enter valid amounts');
      return;
    }

    this.adminService
      .createBill(this.selectedPatient.id, this.doctorFee, this.medicineCost)
      .subscribe({
        next: () => {
          alert('Bill created successfully');
          this.loadPatients();
          this.hideModalById('billCreateModal');
        },
        error: (err) => {
          console.error(err);
          alert('Failed to create bill');
        },
      });
  }

  // View existing bill modal
  showBill(admissionId: number): void {
    this.adminService.getBill(admissionId).subscribe({
      next: (bill) => {
        this.bill = bill;
        this.showModalById('billModal');
      },
      error: (err) => {
        console.error('Failed to load bill', err);
        alert('Could not load bill details');
      },
    });
  }

  // Utility: Show Bootstrap modal by ID
  private showModalById(modalId: string): void {
    const modalEl = document.getElementById(modalId);
    if (modalEl) {
      const modal = new Modal(modalEl);
      modal.show();
    }
  }

  // Utility: Hide Bootstrap modal by ID
  private hideModalById(modalId: string): void {
    const modalEl = document.getElementById(modalId);
    if (modalEl) {
      const modal = Modal.getInstance(modalEl);
      modal?.hide();
    }
  }
}