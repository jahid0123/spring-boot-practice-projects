import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-prescription',
  imports: [NgFor, ReactiveFormsModule],
  templateUrl: './prescription.component.html',
  styleUrl: './prescription.component.css'
})
export class PrescriptionComponent implements OnInit {
  prescriptionForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.prescriptionForm = this.fb.group({
      symptoms: ['', Validators.required],
      diagnosis: ['', Validators.required],
      appointmentId: [null, Validators.required],
      doctorId: [null, Validators.required],
      patientId: [null, Validators.required],
      medicines: this.fb.array([this.createMedicineGroup()])
    });
  }

  get medicines(): FormArray {
    return this.prescriptionForm.get('medicines') as FormArray;
  }

  createMedicineGroup(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      dosage: ['', Validators.required],
      duration: ['', Validators.required]
    });
  }

  addMedicine(): void {
    this.medicines.push(this.createMedicineGroup());
  }

  removeMedicine(index: number): void {
    if (this.medicines.length > 1) {
      this.medicines.removeAt(index);
    }
  }

  onSubmit(): void {
    if (this.prescriptionForm.valid) {
      console.log(this.prescriptionForm.value);
      // send to backend via service
    }
  }
}