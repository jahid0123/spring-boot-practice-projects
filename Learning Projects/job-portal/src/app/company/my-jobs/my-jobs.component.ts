import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CompanyService } from '../service/company.service';
import { CommonModule } from '@angular/common';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-my-jobs',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './my-jobs.component.html',
  styleUrl: './my-jobs.component.css',
})
export class MyJobsComponent implements OnInit {
  jobs: any[] = [];
  jobForm!: FormGroup;
  isEditMode = false;
  editingJobId: number | null = null;

  private modalInstance: Modal | null = null;

  @ViewChild('jobModal') jobModalRef!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {
    this.loadJobs();
    this.initForm();
  }

  initForm(): void {
    this.jobForm = this.fb.group({
      jobTitle: ['', Validators.required],
      jobDescription: [''],
      jobRequirement: [''],
      jobResponsibilities: [''],
      compensationBenefit: [''],
      workPlace: [''],
      employmentStatus: [''],
      jobLocation: ['', Validators.required],
    });
  }

  loadJobs(): void {
    this.companyService.getMyPostedJob().subscribe({
      next: (res) => {
        this.jobs = res;
      },
      error: (err) => {
        console.error('Failed to load jobs:', err);
      },
    });
  }

  openCreateModal(): void {
    this.isEditMode = false;
    this.editingJobId = null;
    this.jobForm.reset();
    this.showBootstrapModal();
  }

  openEditModal(job: any): void {
    this.isEditMode = true;
    this.editingJobId = job.id;
    this.jobForm.patchValue(job);
    this.showBootstrapModal();
  }

  saveJob(): void {
    if (this.jobForm.invalid) return;

    const jobData = this.jobForm.value;
    if (this.isEditMode && this.editingJobId !== null) {
      this.companyService.editJob(this.editingJobId, jobData).subscribe({
        next: () => {
          alert('Updated Job Successfully');
          this.loadJobs();
          this.closeModal();
        },
        error: (err) => {
          console.error('Job update failed:', err);
        },
      });
    } else {
      this.companyService.createJob(jobData).subscribe({
        next: () => {
          alert('Create Job Successfully');
          this.loadJobs();
          this.closeModal();
        },
        error: (err) => {
          console.error('Job creation failed:', err);
        },
      });
    }
  }

  deleteJob(id: number): void {
    if (confirm('Are you sure you want to delete this job?')) {
      this.companyService.deleteJobById(id).subscribe({
        next: () => {
          alert('Delete Job Successfully');
          this.loadJobs();
        },
        error: (err) => {
          console.error('Failed to delete job:', err);
          alert('Deletion failed');
        },
      });
    }
  }

  closeModal(): void {
    if (this.modalInstance) {
      this.modalInstance.hide();
    }
    this.jobForm.reset();
    this.isEditMode = false;
    this.editingJobId = null;
  }

  private showBootstrapModal(): void {
    if (this.jobModalRef) {
      this.modalInstance = new Modal(this.jobModalRef.nativeElement);
      this.modalInstance.show();
    }
  }
  shortListByJobId(jobId: number) {
    this.companyService.allShortListByJobId(jobId).subscribe((res) => {
      this.shortListByJobId = res;
    });
  }
}
