import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PrescriptionService } from './service/prescription.service';
import { GetAppointmentsPatient, PrescriptionDto } from '../../../model/model.classes';
import { DoctorService } from '../service/doctor.service';

@Component({
  selector: 'app-prescription',
  imports: [ ReactiveFormsModule, CommonModule],
  templateUrl: './prescription.component.html',
  styleUrl: './prescription.component.css'
})
export class PrescriptionComponent implements OnInit {


  prescriptionForm!: FormGroup;
  appointments: any[] = [];
  selectedAppointment?: any;

  constructor(
    private fb: FormBuilder,
    private doctorService: DoctorService,
    private prescriptionService: PrescriptionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const doctorId = Number(localStorage.getItem('id'));
    if (doctorId) {
      this.loadAppointments(doctorId);
    }

    this.prescriptionForm = this.fb.group({
      appointmentId: [null, Validators.required],
      patientName: [{ value: '', disabled: true }],
      symptoms: ['', Validators.required],
      diagnosis: ['', Validators.required],
      medicines: this.fb.array([])
    });

    // React to appointment selection
    this.prescriptionForm.get('appointmentId')?.valueChanges.subscribe((id) => {
      this.selectedAppointment = this.appointments.find((a) => a.id === +id);
      this.prescriptionForm.patchValue({
        patientName: this.selectedAppointment?.name || ''
      });
    });

    // Add at least one medicine field by default
    this.addMedicine();
  }

  loadAppointments(doctorId: number) {
    this.doctorService.getAdmittedPatientsByDoctor(doctorId).subscribe({
      next: (data) => (this.appointments = data),
      error: (err) => console.error('Error loading appointments', err)
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
        duration: ['', Validators.required]
      })
    );
  }

  removeMedicine(index: number) {
    this.medicines.removeAt(index);
  }

  onSubmit() {
    if (!this.selectedAppointment) {
      alert('Please select an appointment.');
      return;
    }

    if (this.prescriptionForm.invalid) {
      alert('Please fill all required fields.');
      return;
    }

    const formValue = this.prescriptionForm.getRawValue();

    const prescriptionDto = {
      symptoms: formValue.symptoms,
      diagnosis: formValue.diagnosis,
      medicines: formValue.medicines,
      appointmentId: this.selectedAppointment.id,
      doctorId: this.selectedAppointment.doctorId,
      patientId: this.selectedAppointment.patientId
    };

    this.prescriptionService.createPrescription(prescriptionDto).subscribe({
      next: () => {
        alert('Prescription created successfully!');
        this.router.navigateByUrl('/doctor/doctor-appointment');
      },
      error: (err) => {
        console.error('Error saving prescription', err);
        alert('Oops! Already prescribed or server error.');
        this.router.navigateByUrl('/doctor/doctor-appointment');
      }
    });
  }
}