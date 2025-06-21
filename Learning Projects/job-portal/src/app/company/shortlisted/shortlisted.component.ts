import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../service/company.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-shortlisted',
  imports: [CommonModule],
  templateUrl: './shortlisted.component.html',
  styleUrl: './shortlisted.component.css'
})
export class ShortlistedComponent implements OnInit {
  jobs: any[] = [];
  selectedJob: any = null;
  shortlists: any[] = [];

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.companyService.getMyPostedJob().subscribe({
      next: (res) => {
        this.jobs = res;
      },
      error: (err) => {
        console.error('Failed to load jobs:', err);
      }
    });
  }

  viewShortlists(job: any): void {
    this.selectedJob = job;
    this.companyService.allShortListByJobId(job.id).subscribe({
      next: (res) => {
        this.shortlists = res;
      },
      error: (err) => {
        console.error('Failed to load shortlists:', err);
        this.shortlists = [];
      }
    });
  }
}