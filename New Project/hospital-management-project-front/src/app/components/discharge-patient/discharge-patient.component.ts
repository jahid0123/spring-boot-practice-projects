import { Component, OnInit } from '@angular/core';
import { Admission, IndoorService } from '../../services/indoor.service';
import { AdmissionService } from '../../services/admission.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-discharge-patient',
  imports: [NgIf, NgFor],
  templateUrl: './discharge-patient.component.html',
  styleUrl: './discharge-patient.component.css'
})
export class DischargePatientComponent implements OnInit {

  admissions: Admission[] = [];
  message = '';

  constructor(
    private admissionService: AdmissionService,
    private indoorService: IndoorService
  ) {}

  ngOnInit(): void {
    this.admissionService.getAllAdmissions().subscribe(data => {
      this.admissions = data.filter(a => !a.discharged);
    });
  }

  discharge(admissionId: number): void {
    this.indoorService.dischargePatient(admissionId).subscribe({
      next: () => {
        this.message = '✅ Patient discharged successfully';
        this.admissions = this.admissions.filter(a => a.id !== admissionId);
      },
      error: err => this.message = '❌ Error: ' + err.error
    });
  }
}