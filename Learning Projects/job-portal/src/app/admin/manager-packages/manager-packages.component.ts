import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, NgFor, NgIf, NgStyle } from '@angular/common';

@Component({
  selector: 'app-manager-packages',
  imports: [CommonModule, NgIf, NgFor, ReactiveFormsModule],
  templateUrl: './manager-packages.component.html',
  styleUrl: './manager-packages.component.css'
})
export class ManagePackagesComponent implements OnInit {
  packages: any[] = [];
  showModal = false;
  isEditMode = false;
  selectedPackage: any = null;
  packageForm!: FormGroup;

  constructor(private adminService: AdminService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadPackages();
    this.initForm();
  }

  loadPackages(): void {
    this.adminService.getAllPackage().subscribe(res => this.packages = res);
  }

  initForm(): void {
    this.packageForm = this.fb.group({
      id: [],
      name: ['', Validators.required],
      jobPostLimit: [0, Validators.required],
      applicantViewLimit: [0, Validators.required],
      validityMonth: [0, Validators.required],
      price: [0, Validators.required]
    });
  }

  openCreateModal(): void {
    this.packageForm.reset();
    this.isEditMode = false;
    this.showModal = true;
  }

  openEditModal(pkg: any): void {
    this.packageForm.patchValue(pkg);
    this.isEditMode = true;
    this.showModal = true;
  }

  savePackage(): void {
    const pkg = this.packageForm.value;
    if (this.isEditMode) {
      this.adminService.editPackage(pkg).subscribe(() => {
        alert('Update Package Successfully');
        this.loadPackages();
        this.closeModal();
      });
    } else {
      this.adminService.createPackage(pkg).subscribe(() => {
        alert('Create Package Successfully');
        this.loadPackages();
        this.closeModal();
      });
    }
  }

  deletePackage(id: number): void {
    if (confirm('Are you sure you want to delete this package?')) {
      this.adminService.deletePackage(id).subscribe(() => {
        alert('Deletion Package Successfully');
        this.loadPackages();
      });
    }
  }

  closeModal(): void {
    this.showModal = false;
    this.selectedPackage = null;
  }
}
