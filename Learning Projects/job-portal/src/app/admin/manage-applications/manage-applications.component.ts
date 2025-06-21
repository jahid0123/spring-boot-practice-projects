import { Component, OnInit } from '@angular/core';
import { JobApply } from '../../model/model';
import { AdminService } from '../service/admin.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-manage-applications',
  imports: [NgFor, CommonModule, ReactiveFormsModule],
  templateUrl: './manage-applications.component.html',
  styleUrl: './manage-applications.component.css',
})
export class ManageApplicationsComponent implements OnInit {

  jobApplyList: JobApply[] = [];
  selectedApplication: JobApply | null = null;
  showModal: boolean = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications(): void {
    this.adminService.getAllApplies().subscribe({
      next: (data) => {
        this.jobApplyList = data;
      },
      error: (err) => {
        console.error('Failed to load applications:', err);
      }
    });
  }

  deleteApplication(id: number): void {
    if (confirm('Are you sure you want to delete this job application?')) {
      this.adminService.deleteApplication(id).subscribe({
        next: () => {
          alert('Application deleted successfully');
          this.loadApplications();
        },
        error: (err) => {
          console.error(err);
          alert('Application deletion failed!!');
        },
      });
    }
  }

  viewDetails(application: JobApply): void {
    this.selectedApplication = application;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.selectedApplication = null;
  }
}