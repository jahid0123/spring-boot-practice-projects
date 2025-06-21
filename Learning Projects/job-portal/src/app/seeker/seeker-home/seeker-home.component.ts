import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../company/service/company.service';
import { SeekerService } from '../service/seeker.service';
import { CommonModule, NgFor } from '@angular/common';

@Component({
  selector: 'app-seeker-home',
  imports: [NgFor, CommonModule],
  templateUrl: './seeker-home.component.html',
  styleUrl: './seeker-home.component.css',
})
export class SeekerHomeComponent implements OnInit {

  jobs: any[] = [];

  constructor(private seekerService: SeekerService) {}

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.seekerService.getAllJobs().subscribe({
      next: (res) => {
        this.jobs = res;
      },
      error: (err) => {
        console.error('Failed to load jobs:', err);
      },
    });
  }

   addToFavorites(job: any): void {
    alert(`Added "${job.jobTitle}" to favorites.`);
  }

  applyToJob(id: number): void {
    const jobId = id;
    const seekerId = Number(localStorage.getItem('id'));
    this.seekerService.applyJob({seekerId, jobId}).subscribe({
      next: res=>{
        alert('Applied Successfully.')
      },
      error: err=>{
        console.error(err);
        alert('Ops!!.. Failed to apply!!!')
      }
    });
  }
}
