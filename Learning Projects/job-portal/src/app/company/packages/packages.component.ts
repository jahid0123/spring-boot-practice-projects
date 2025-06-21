import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../service/company.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-packages',
  imports: [NgFor],
  templateUrl: './packages.component.html',
  styleUrl: './packages.component.css'
})
export class PackagesComponent implements OnInit {
  packages: any[] = [];

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.companyService.getAllPackages().subscribe((res) => {
      this.packages = res;
    });
  }

  buyPackage(packageId: number, pkg: string): void {
    const companyId = Number(localStorage.getItem('id'));
    this.companyService.purchasePackage({companyId, packageId}).subscribe({
      next: res=>{
        alert(`Package ${pkg} purchase successfully.`);
      },
      error: err=>{
        console.error(err);
        alert(`Package ${pkg} purchase failed!!.`);
      }
    });
       
  }
}