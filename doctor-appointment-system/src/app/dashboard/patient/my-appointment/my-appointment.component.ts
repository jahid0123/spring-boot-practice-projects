import { Component } from '@angular/core';
import { MyAppointmentService } from './service/my-appointment.service';
import { GetAppointmentsPatient } from '../../../model/model.classes';
import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-my-appointment',
  imports: [NgClass, NgFor, ReactiveFormsModule, CommonModule],
  templateUrl: './my-appointment.component.html',
  styleUrl: './my-appointment.component.css'
})
export class MyAppointmentComponent {

 appointments: GetAppointmentsPatient[] = [];

  constructor(private appointmentService: MyAppointmentService) {}

  ngOnInit(): void {
    this.loadAppointments();
  }

  loadAppointments(): void {
    this.appointmentService.getAppointments().subscribe(data => {
      this.appointments = data;
    });
  }

  downloadPrescription(appointmentId: number): void {
  // Replace with real implementation
  // e.g., navigate to download URL or call API and save file
  console.log('Downloading prescription for appointment:', appointmentId);
}

}
