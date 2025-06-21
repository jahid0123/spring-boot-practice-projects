import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../service/company.service';
import { EditCompanyProfile } from '../../model/model';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-company-profile',
  imports: [FormsModule, NgIf],
  templateUrl: './company-profile.component.html',
  styleUrl: './company-profile.component.css'
})
export class CompanyProfileComponent implements OnInit {

  compnay: any | undefined;
  editedCompany: EditCompanyProfile = { id: 0, name: '', business: '', address: '', phone: '' };
  isEditing = false;

  constructor(private adminService: CompanyService) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(): void {
    this.adminService.getMyProfileInfo().subscribe({
      next: (data: any) => {
        this.compnay = data;
      },
      error: (err) => {
        console.error('Failed to load company profile:', err);
      }
    });
  }

  openEditModal(): void {
    if (this.compnay) {
      this.editedCompany = {
        id: this.compnay.id,
        name: this.compnay.name,
        business: this.compnay.business,
        address: this.compnay.address,
        phone: this.compnay.phone
      };
      this.isEditing = true;
    }
  }

  saveChanges(): void {
    if (!this.editedCompany) return;

    this.adminService.editCompanyInfo(this.editedCompany).subscribe({
      next: (updated: any) => {
        this.compnay = updated;
        this.isEditing = false;
        alert('Profile updated successfully!');
        this.loadProfile();
      },
      error: (err) => {
        console.error('Update failed:', err);
        alert('Failed to update profile!');
      }
    });
  }

  cancelEdit(): void {
    this.isEditing = false;
  }
}