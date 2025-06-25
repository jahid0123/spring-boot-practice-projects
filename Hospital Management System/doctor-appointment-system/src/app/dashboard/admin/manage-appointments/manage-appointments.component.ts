import { Component, OnInit } from '@angular/core';
import { GetAppointmentsPatient } from '../../../model/model.classes';
import { Router } from '@angular/router';
import { DoctorAppointmentService } from '../../doctor/doctor-appointment/service/doctor-appointment.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-manage-appointments',
  imports: [NgFor, NgIf, CommonModule],
  templateUrl: './manage-appointments.component.html',
  styleUrl: './manage-appointments.component.css',
})
export class ManageAppointmentsComponent implements OnInit {
  appointments: GetAppointmentsPatient[] = [];
  //approvedAppointments: GetAppointmentsPatient[] = [];

  constructor(
    private router: Router,
    private doctorService: DoctorAppointmentService,
  ) {}

  ngOnInit(): void {
    this.doctorService.getAllAppointments().subscribe({
      next: (res) => {
        //this.appointments = res;
        this.appointments = res.filter(
          (appointment) =>
            appointment.appointmentStatus?.toUpperCase() === 'PENDING',
        );
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  updateStatus(appointmentId: number, status: 'CONFIRMED' | 'REJECTED') {
    // Call service to update status
    this.doctorService.updateStatus(appointmentId, status).subscribe({
      next: () => {
        alert(`Appointment ${status.toLowerCase()} successfully.`);
        // Optionally refresh list
        this.ngOnInit();
      },
      error: () => alert('Failed to update status.'),
    });
  }

}
