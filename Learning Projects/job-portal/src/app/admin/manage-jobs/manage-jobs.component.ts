import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { Job } from '../../model/model';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-manage-jobs',
  imports: [NgFor],
  templateUrl: './manage-jobs.component.html',
  styleUrl: './manage-jobs.component.css',
})
export class ManageJobsComponent implements OnInit {
  jobList: Job[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.adminService.getAllJobs().subscribe((data) => {
      this.jobList = data;
    });
  }

  deleteJob(id: number) {
    if (confirm('Are you sure you want to delete this job?')) {
      this.adminService.deleteJob(id).subscribe({
        next: (res) => {
          alert('Job deleted successfully');
          this.ngOnInit(); // Refresh the list
        },
        error: (err) => {
          console.error(err);
          alert('Job deletion failed!!');
        },
      });
    }
  }
}
