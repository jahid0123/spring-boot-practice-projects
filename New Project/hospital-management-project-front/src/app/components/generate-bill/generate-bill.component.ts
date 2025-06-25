import { Component, OnInit } from '@angular/core';
import { Bill, IndoorService } from '../../services/indoor.service';
import { AdmissionService } from '../../services/admission.service';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-generate-bill',
  imports: [FormsModule, NgIf],
  templateUrl: './generate-bill.component.html',
  styleUrl: './generate-bill.component.css'
})
export class GenerateBillComponent implements OnInit {

  dischargedAdmissions: any[] = [];
  selectedAdmissionId: number = 0;
  roomCharge = 1000;
  doctorFee = 500;
  medicineCost = 200;
  bill?: Bill;
  message = '';

  constructor(
    private indoorService: IndoorService,
    private admissionService: AdmissionService
  ) {}

  ngOnInit(): void {
    this.admissionService.getAllAdmissions().subscribe(data => {
      this.dischargedAdmissions = data.filter(a => a.discharged);
    });
  }

  generate(): void {
    if (!this.selectedAdmissionId) return;

    this.indoorService.generateBill(
      this.selectedAdmissionId,
      this.roomCharge,
      this.doctorFee,
      this.medicineCost
    ).subscribe({
      next: (data) => {
        this.bill = data;
        this.message = '✅ Bill generated successfully!';
      },
      error: err => {
        this.message = '❌ Error: ' + err.error;
      }
    });
  }
}