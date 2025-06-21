import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-manage-users',
  imports: [RouterModule, CommonModule, NgFor, NgIf, NgClass ],
  templateUrl: './manage-users.component.html',
  styleUrl: './manage-users.component.css'
})
export class ManageUsersComponent implements OnInit {
  selected: 'company' | 'seeker' | 'admin' = 'company';

  companies: any[] = [];
  jobSeekers: any[] = [];
  users: any[] = [];

  currentData: any[] = [];
  columns: string[] = [];
  keys: string[] = [];

  selectedApplication: any | null = null;
  showModal: boolean = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.reloadAllData();
    this.selectTab(this.selected); // Set default tab
  }

  reloadAllData(): void {
    this.adminService.getAllCompanies().subscribe((res) => {
      this.companies = res;
      if (this.selected === 'company') this.selectTab('company');
    });

    this.adminService.getAllSeekers().subscribe((res) => {
      this.jobSeekers = res;
      if (this.selected === 'seeker') this.selectTab('seeker');
    });

    this.adminService.getAllUsers().subscribe((res) => {
      this.users = res;
      if (this.selected === 'admin') this.selectTab('admin');
    });
  }

  selectTab(type: 'company' | 'seeker' | 'admin'): void {
    this.selected = type;
    if (type === 'company') {
      this.currentData = this.companies;
    } else if (type === 'seeker') {
      this.currentData = this.jobSeekers;
    } else {
      this.currentData = this.users;
    }

    if (this.currentData.length > 0) {
      this.keys = Object.keys(this.currentData[0]);
      this.columns = this.keys.map((k) =>
        k.replace(/([A-Z])/g, ' $1').replace(/^./, (c) => c.toUpperCase())
      );
    } else {
      this.keys = [];
      this.columns = [];
    }
  }

  reloadData(): void {
    this.reloadAllData();
  }

  deleteUser(id: number): void {
    if (!confirm(`Are you sure you want to delete this ${this.selected}?`)) return;

    let deleteObservable;

    if (this.selected === 'company') {
      deleteObservable = this.adminService.deleteCompany(id);
    } else if (this.selected === 'seeker') {
      deleteObservable = this.adminService.deleteSeeker(id);
    } else {
      deleteObservable = this.adminService.deleteAdmin(id);
    }

    deleteObservable.subscribe({
      next: () => {
        alert(`${this.selected} deleted successfully`);
        this.reloadAllData();
      },
      error: (err) => {
        console.error(err);
        alert(`${this.selected} deletion failed!!`);
      },
    });
  }

  viewDetails(data: any): void {
    this.selectedApplication = data;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.selectedApplication = null;
  }
}
