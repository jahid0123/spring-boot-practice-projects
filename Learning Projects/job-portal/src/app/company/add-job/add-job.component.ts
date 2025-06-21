import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CompanyService } from '../service/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-job',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-job.component.html',
  styleUrl: './add-job.component.css'
})
export class AddJobComponent implements OnInit {
  subscriptions: any;

  constructor(private companyService: CompanyService, private router: Router) {}

  ngOnInit(): void {
    this.companyService.getMyPurchagePackage().subscribe((data) => {
      this.subscriptions = data;
    });
  }

  buyPackage(): void {
    this.router.navigateByUrl('/company-dashboard/packages');
  }
}
