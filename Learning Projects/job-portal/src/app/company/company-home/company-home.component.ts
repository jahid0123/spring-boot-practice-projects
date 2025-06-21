import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../service/company.service';
import { CommonModule, NgFor } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-company-home',
  imports: [NgFor, CommonModule, ReactiveFormsModule],
  templateUrl: './company-home.component.html',
  styleUrl: './company-home.component.css'
})
export class CompanyHomeComponent implements OnInit {
  jobs: any[] = [];
  selectedJob: any = null;
  applicants: any[] = [];

  showShortlistModal = false;
  selectedApply: any = null;
  shortlistForm!: FormGroup;

  constructor(private companyService: CompanyService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadJobs();
    this.shortlistForm = this.fb.group({
      interviewDate: ['', Validators.required]
    });
  }

  loadJobs(): void {
    this.companyService.getMyPostedJob().subscribe({
      next: (res) => {
        this.jobs = res;
      },
      error: (err) => {
        console.error('❌ Failed to load jobs:', err);
      }
    });
  }

  viewApplicants(job: any): void {
    this.selectedJob = job;

    this.companyService.getMyPostedJobAppliesByJobId(job.id).subscribe({
      next: (res) => {
        this.applicants = res; // Assuming API returns array of JobApply
      },
      error: (err) => {
        console.error('❌ Failed to load applicants:', err);
        this.applicants = [];
      }
    });
  }

  openShortlistModal(apply: any): void {
    this.selectedApply = apply;
    this.showShortlistModal = true;
  }

  closeShortlistModal(): void {
    this.showShortlistModal = false;
    this.shortlistForm.reset();
  }

  shortlist(): void {
    if (!this.selectedJob || !this.selectedJob.company?.id) return;

    const payload = {
      jobApplyId: this.selectedApply.id, // Make sure `seeker` contains jobApplyId or adapt accordingly
      companyId: this.selectedJob.company.id,
      interviewDate: this.shortlistForm.value.interviewDate
    };

    this.companyService.createShortlist(payload).subscribe({
      next: () => {
        alert(`✅ Shortlisted ${this.selectedApply.name} for job ${this.selectedJob.jobTitle}`);
        this.closeShortlistModal();
      },
      error: (err) => {
        console.error('❌ Failed to shortlist:', err);
        alert('Already shortlisted!!!!');
      }
    });
  }
}