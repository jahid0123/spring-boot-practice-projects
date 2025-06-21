import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../service/company.service';
import { CommonModule, NgFor } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-applicants',
  imports: [NgFor, ReactiveFormsModule, CommonModule],
  templateUrl: './applicants.component.html',
  styleUrl: './applicants.component.css'
})
export class ApplicantsComponent implements OnInit {
  myJobApplies: any[] = [];
  showShortlistModal = false;
  selectedApply: any = null;
  shortlistForm!: FormGroup;

  constructor(private companyService: CompanyService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadJobApplies();

    this.shortlistForm = this.fb.group({
      interviewDate: ['', Validators.required]
    });
  }

  loadJobApplies(): void {
    this.companyService.getMyPostedJobApplies().subscribe((res) => {
      this.myJobApplies = res;
    });
  }

  viewDetails(data: any): void {
    console.log('Viewing details for:', data);
  }

  openShortlistModal(apply: any): void {
    this.selectedApply = apply;
    this.showShortlistModal = true;
  }

  closeShortlistModal(): void {
    this.showShortlistModal = false;
    this.shortlistForm.reset();
  }

  submitShortlist(): void {
    if (this.shortlistForm.invalid || !this.selectedApply) return;

    const payload = {
      jobApplyId: this.selectedApply.id,
      companyId: this.selectedApply.job.company.id,
      interviewDate: this.shortlistForm.value.interviewDate
    };

    this.companyService.createShortlist(payload).subscribe({
      next: () => {
        alert('Applicant shortlisted successfully!');
        this.selectedApply.shortlisted = true;
        this.closeShortlistModal();
      },
      error: (err) => {
        console.error(err);
        alert('Failed to shortlist applicant.');
      }
    });
  }
}