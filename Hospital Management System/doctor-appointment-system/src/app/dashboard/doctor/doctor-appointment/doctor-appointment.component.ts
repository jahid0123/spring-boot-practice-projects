import { CommonModule, NgClass, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { GetAppointmentsPatient } from '../../../model/model.classes';
import { Router } from '@angular/router';
import { DoctorService } from '../service/doctor.service';

@Component({
  selector: 'app-doctor-appointment',
  imports: [  ReactiveFormsModule, CommonModule],
  templateUrl: './doctor-appointment.component.html',
  styleUrl: './doctor-appointment.component.css'
})
export class DoctorAppointmentComponent implements OnInit{


  appointments: any[] = [];
  //approvedAppointments: GetAppointmentsPatient[] = [];


  constructor(private router: Router, private doctorService: DoctorService){}


  ngOnInit(): void {
    const doctorId = Number(localStorage.getItem('id'))

    this.doctorService.getAdmittedPatientsByDoctor(doctorId).subscribe({
      next: (res) => {
        //this.appointments = res;
        this.appointments = res;
      },
      error: (err) =>{
        console.error(err);
      }
    });
    
  }

generatePrescription(appt: GetAppointmentsPatient) {
  this.router.navigateByUrl('/doctor/prescription'), {state: { appointment: appt}};
}

}
