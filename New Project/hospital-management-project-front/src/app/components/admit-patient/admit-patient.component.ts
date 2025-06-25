import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Bed, IndoorService } from '../../services/indoor.service';
import { PatientService } from '../../services/patient.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admit-patient',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './admit-patient.component.html',
  styleUrl: './admit-patient.component.css'
})
export class AdmitPatientComponent implements OnInit {

  admitForm!: FormGroup;
  beds: Bed[] = [];
  patients: any[] = [];
  message = '';

  constructor(
    private indoorService: IndoorService,
    private patientService: PatientService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.admitForm = this.fb.group({
      patientId: [''],
      bedId: ['']
    });

    this.indoorService.getAvailableBeds().subscribe(beds => this.beds = beds);
    this.patientService.getAllPatients().subscribe(data => this.patients = data);
  }

  admit(): void {
    const { patientId, bedId } = this.admitForm.value;
    this.indoorService.admitPatient(patientId, bedId).subscribe({
      next: () => this.message = '✅ Patient admitted successfully!',
      error: err => this.message = '❌ Error: ' + err.error
    });
  }
}