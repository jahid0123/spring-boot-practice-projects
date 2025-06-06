import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-seller-dashboard',
  imports: [RouterModule, NgFor],
  templateUrl: './seller-dashboard.component.html',
  styleUrl: './seller-dashboard.component.css'
})
export class SellerDashboardComponent {


    constructor(private router: Router) {}

  logout() {
    // Clear local storage or auth token
    localStorage.clear();
    // Navigate to login
    this.router.navigate(['/login']);
  }
}
