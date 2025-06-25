import { NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PatientHomeService } from '../patient-home/service/patient-home.service';
import { Router } from '@angular/router';
import { DoctorListDto, DoctorResponseDto } from '../../../model/model.classes';
import { PatientAppointmentService } from './service/patient-appointment.service';

@Component({
  selector: 'app-patient-appointment',
  imports: [ReactiveFormsModule, NgFor, NgClass, NgIf],
  templateUrl: './patient-appointment.component.html',
  styleUrl: './patient-appointment.component.css'
})

export class PatientAppointmentComponent implements OnInit {

  appointmentForm!: FormGroup;
  doctors: DoctorListDto[] = [];
  selectedDoctor?: DoctorListDto;

  constructor(
    private fb: FormBuilder,
    private patientHomeService: PatientHomeService,
    private appointmentService: PatientAppointmentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Get doctor from router state if passed
    const nav = this.router.getCurrentNavigation();
    this.selectedDoctor = nav?.extras?.state?.['doctor'];

    const storedPatientId = Number(localStorage.getItem('id'));

    // Initialize the form with preselected doctor id or null
    this.appointmentForm = this.fb.group({
      doctorId: [this.selectedDoctor?.id || null, Validators.required],
      patientId: [storedPatientId],
      appointmentDate: ['', Validators.required],
      appointmentTime: ['', Validators.required],
    });

    // Always load doctors list (needed for dropdown options)
    this.patientHomeService.getAllDoctors().subscribe({
      next: (data) => (this.doctors = data),
      error: (err) => console.error('Error fetching doctors:', err),
    });
  }

  onSubmit(): void {
    if (this.appointmentForm.valid) {
      const appointment = this.appointmentForm.value;

      this.appointmentService.bookAppointment(appointment).subscribe({
        next: () => {
          alert('Appointment booked successfully!');
          this.router.navigate(['/patient']);
        },
        error: (err) => {
          console.error('Appointment booking failed:', err);
          alert('Failed to book appointment.');
        },
      });
     
    } else {
      alert('Please fill out the form correctly.');
    }
  }
}