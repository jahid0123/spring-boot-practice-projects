import { CommonModule, NgClass, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { GetAppointmentsPatient } from '../../../model/model.classes';
import { Router } from '@angular/router';
import { DoctorAppointmentService } from './service/doctor-appointment.service';

@Component({
  selector: 'app-doctor-appointment',
  imports: [NgClass, NgIf, ReactiveFormsModule, CommonModule],
  templateUrl: './doctor-appointment.component.html',
  styleUrl: './doctor-appointment.component.css'
})
export class DoctorAppointmentComponent implements OnInit{


  appointments: GetAppointmentsPatient[] = [];
  //approvedAppointments: GetAppointmentsPatient[] = [];


  constructor(private router: Router, private doctorService: DoctorAppointmentService){}


  ngOnInit(): void {

    this.doctorService.getAppointmentsByDoctorId().subscribe({
      next: (res) => {
        //this.appointments = res;
        this.appointments = res.filter(appointment => appointment.appointmentStatus?.toUpperCase() === 'CONFIRMED');
      },
      error: (err) =>{
        console.error(err);
      }
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
    error: () => alert('Failed to update status.')
  });
}

generatePrescription(appt: GetAppointmentsPatient) {
  this.router.navigateByUrl('/doctor/prescription'), {state: { appointment: appt}};
}

}
