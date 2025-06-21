import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../service/company.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-all-bills',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './all-bills.component.html',
  styleUrl: './all-bills.component.css',
})
export class AllBillsComponent implements OnInit {
  bills: any[] = [];

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.companyService.getAllBills().subscribe((res) => {
      this.bills = res;
    });
  }

  viewDetails(data: any){
    
  }
}
