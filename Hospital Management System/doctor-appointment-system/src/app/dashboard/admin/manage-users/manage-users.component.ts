import { NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ManageUsersService } from './service/manage-users.service';

@Component({
  selector: 'app-manage-users',
  imports: [NgIf, NgClass, NgFor],
  templateUrl: './manage-users.component.html',
  styleUrl: './manage-users.component.css',
})
export class ManageUsersComponent implements OnInit {
  selected: 'doctor' | 'patient' | 'user' = 'doctor';

  doctors: any[] = [];
  patients: any[] = [];
  users: any[] = [];

  currentData: any[] = [];
  columns: string[] = [];
  keys: string[] = [];

  constructor(private userService: ManageUsersService) {}

  ngOnInit(): void {
    this.userService.getAllDoctors().subscribe((res) => {
      this.doctors = res;
    });

    this.userService.getAllPatients().subscribe((res) => {
      this.patients = res;
    });

    this.userService.getAllUsers().subscribe((res) => {
      this.users = res;
    });
  }

  selectTab(type: 'doctor' | 'patient' | 'user'): void {
    this.selected = type;
    if (type === 'doctor') {
      this.currentData = this.doctors;
    } else if (type === 'patient') {
      this.currentData = this.patients;
    } else {
      this.currentData = this.users;
    }

    // Set column names and keys
    if (this.currentData.length > 0) {
      this.keys = Object.keys(this.currentData[0]);
      this.columns = this.keys.map((k) =>
        k.replace(/([A-Z])/g, ' $1').replace(/^./, (c) => c.toUpperCase()),
      );
    } else {
      this.keys = [];
      this.columns = [];
    }
  }

  reloadData(): void {
    alert(`Reloading ${this.selected}s data...`);
  }
}
