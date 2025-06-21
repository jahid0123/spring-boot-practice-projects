import { Component, OnInit } from '@angular/core';
import { SeekerService } from '../service/seeker.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-seeker-shortlist',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './seeker-shortlist.component.html',
  styleUrl: './seeker-shortlist.component.css'
})
export class SeekerShortlistComponent implements OnInit {

  jobs: any[] = [];

  constructor(private seekerService: SeekerService) {}

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.seekerService.getShortlistMyAppliedJob().subscribe({
      next: (res) => {
        this.jobs = res;
      },
      error: (err) => {
        console.error('Failed to load jobs:', err);
      },
    });
  }

  

}
