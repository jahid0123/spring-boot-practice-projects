import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-admin-dashboard',
  imports: [],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})
export class AdminDashboardComponent implements OnInit {
  jobList: any[] = [];
  applicationList: any[] = [];
  jobSeekerList: any[] = [];
  companyList: any[] = [];
  adminList: any[] = [];
  packageList: any[] = [];
  billList: any[] = [];
  totalPrice: number = 0;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.adminService.getAllJobs().subscribe((data) => {
      this.jobList = data;
    });

    this.adminService.getAllApplies().subscribe((data) => {
      this.applicationList = data;
    });

    this.adminService.getAllSeekers().subscribe((data) => {
      this.jobSeekerList = data;
    });

    this.adminService.getAllUsers().subscribe((data) => {
      this.adminList = data;
    });

    this.adminService.getAllCompanies().subscribe((data) => {
      this.companyList = data;
    });

    this.adminService.getAllPackage().subscribe((data) => {
      this.packageList = data;
    });

    this.adminService.getAllBills().subscribe((data) => {
      console.log('Bill list:', data); // Check if it's an array
      this.billList = data;

      if (Array.isArray(this.billList)) {
        this.totalPrice = this.billList.reduce(
          (sum, bill) => sum + bill.pack.price, 0
        );
      }
    });
  }
}
