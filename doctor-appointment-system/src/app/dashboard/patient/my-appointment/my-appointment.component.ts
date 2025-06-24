import { Component, OnInit } from '@angular/core';
import { MyAppointmentService } from './service/my-appointment.service';
import {
  GetAppointmentsPatient,
  PrescriptionResponseDto,
} from '../../../model/model.classes';
import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-my-appointment',
  imports: [NgClass, NgFor, ReactiveFormsModule, CommonModule],
  templateUrl: './my-appointment.component.html',
  styleUrl: './my-appointment.component.css',
})
export class MyAppointmentComponent implements OnInit {
  appointments: GetAppointmentsPatient[] = [];
  loading: boolean = false;
  error: string | null = null;

  constructor(
    private http: HttpClient,
    private appointmentService: MyAppointmentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAppointments();
  }

  loadAppointments(): void {
    this.loading = true;
    this.error = null;

    this.appointmentService.getAppointments().subscribe({
      next: (data: GetAppointmentsPatient[]) => {
        this.appointments = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load appointments';
        this.loading = false;
        console.error('Error loading appointments:', err);
      }
    });
  }

  viewPrescription(appointmentId: number): void {
    // Open PDF in new tab
    this.http.get(`http://localhost:8081/api/prescriptions/${appointmentId}/report`, {
      responseType: 'blob'
    }).subscribe({
      next: (blob: Blob) => {
        const file = new Blob([blob], { type: 'application/pdf' });
        const fileURL = URL.createObjectURL(file);
        window.open(fileURL);
        // Note: no revokeObjectURL here immediately, or the tab may close the URL before loading
      },
      error: (err) => {
        console.error('View prescription failed:', err);
        alert('Failed to view prescription.');
      }
    });
  }

  downloadPrescription(appointmentId: number): void {
    // Force file download
    this.http.get(`http://localhost:8081/api/prescriptions/${appointmentId}/report`, {
      responseType: 'blob'
    }).subscribe({
      next: (blob: Blob) => {
        const file = new Blob([blob], { type: 'application/pdf' });
        const fileURL = URL.createObjectURL(file);
        const a = document.createElement('a');
        a.href = fileURL;
        a.download = `Prescription_${appointmentId}.pdf`;
        document.body.appendChild(a); // Firefox requires appendChild
        a.click();
        a.remove();
        URL.revokeObjectURL(fileURL);
      },
      error: (err) => {
        console.error('Download failed:', err);
        alert('Failed to download prescription.');
      }
    });
  }

  trackById(index: number, item: GetAppointmentsPatient): number {
    return item.appointmentId; // Use appointmentId to uniquely identify appointments
  }
}
