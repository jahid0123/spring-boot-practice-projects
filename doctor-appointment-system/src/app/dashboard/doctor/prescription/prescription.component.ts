import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PrescriptionService } from './service/prescription.service';
import { GetAppointmentsPatient, PrescriptionDto } from '../../../model/model.classes';

@Component({
  selector: 'app-prescription',
  imports: [NgFor, ReactiveFormsModule, NgIf],
  templateUrl: './prescription.component.html',
  styleUrl: './prescription.component.css'
})
export class PrescriptionComponent implements OnInit {

  prescriptionForm!: FormGroup;
  appointments: GetAppointmentsPatient[] = [];
  selectedAppointment?: GetAppointmentsPatient;

  constructor(
    private fb: FormBuilder,
    private prescriptionService: PrescriptionService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Suppose doctorId is passed in router state or query param
    const doctorId = Number(localStorage.getItem('id'));
    if (doctorId) {
      this.loadApprovedAppointments(doctorId);
    }

    this.prescriptionForm = this.fb.group({
      appointmentId: [null, Validators.required],
      patientName: [{ value: '', disabled: true }],
      symptoms: ['', Validators.required],
      diagnosis: ['', Validators.required],
      medicines: this.fb.array([]),
    });

    // React to appointment selection to show patientName
    this.prescriptionForm.get('appointmentId')?.valueChanges.subscribe((id) => {
      this.selectedAppointment = this.appointments.find((a) => a.appointmentId === +id);
      this.prescriptionForm.patchValue({
        patientName: this.selectedAppointment?.patientName || '',
      });
    });
  }

  loadApprovedAppointments(doctorId: number) {
    this.prescriptionService.getApprovedAppointments(doctorId).subscribe({
      next: (data) => (this.appointments = data.filter(appointment => appointment.appointmentStatus?.toUpperCase() === 'CONFIRMED')),
      error: (err) => console.error('Error loading appointments', err),
    });
  }

  get medicines() {
    return this.prescriptionForm.get('medicines') as FormArray;
  }

  addMedicine() {
    this.medicines.push(
      this.fb.group({
        name: ['', Validators.required],
        dosage: ['', Validators.required],
        frequency: ['', Validators.required],
        duration: ['', Validators.required],
      })
    );
  }

  removeMedicine(index: number) {
    this.medicines.removeAt(index);
  }

  onSubmit() {
    if (!this.selectedAppointment) {
      alert('Please select a valid appointment.');
      return;
    }

    if (this.prescriptionForm.invalid) {
      alert('Please fill all required fields.');
      return;
    }

    const formValue = this.prescriptionForm.getRawValue();

    const prescriptionDto: PrescriptionDto = {
      symptoms: formValue.symptoms,
      diagnosis: formValue.diagnosis,
      medicines: formValue.medicines,
      appointmentId: this.selectedAppointment.appointmentId,
      doctorId: this.selectedAppointment.doctorId,
      patientId: this.selectedAppointment.patientId,
    };

    this.prescriptionService.createPrescription(prescriptionDto).subscribe({
      next: () => {
        alert('Prescription created successfully!');
        this.prescriptionForm.reset();
        this.router.navigateByUrl('/doctor/doctor-appointment'); // Or wherever
      },
      error: (error) => {
        console.error('Error saving prescription', error);
        alert('Ops, Already prescribed!!!');
         this.router.navigateByUrl('/doctor/doctor-appointment');
      },
    });
  }
}