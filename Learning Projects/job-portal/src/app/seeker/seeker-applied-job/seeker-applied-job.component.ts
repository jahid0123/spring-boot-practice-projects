import { Component, OnInit } from '@angular/core';
import { SeekerService } from '../service/seeker.service';
import { CommonModule, NgFor } from '@angular/common';

@Component({
  selector: 'app-seeker-applied-job',
  imports: [CommonModule, NgFor],
  templateUrl: './seeker-applied-job.component.html',
  styleUrl: './seeker-applied-job.component.css'
})
export class SeekerAppliedJobComponent implements OnInit {

  jobs: any[] = [];

  constructor(private seekerService: SeekerService) {}

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.seekerService.getMyAppliedJobs().subscribe({
      next: (res) => {
        this.jobs = res;
      },
      error: (err) => {
        console.error('Failed to load jobs:', err);
      },
    });
  }

  

}
