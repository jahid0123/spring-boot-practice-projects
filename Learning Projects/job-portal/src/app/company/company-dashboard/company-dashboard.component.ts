import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-company-dashboard',
  imports: [RouterModule, RouterOutlet],
  templateUrl: './company-dashboard.component.html',
  styleUrl: './company-dashboard.component.css'
})
export class CompanyDashboardComponent {


  constructor(private router: Router){}



  logout() {
    // Clear local storage or auth token
    localStorage.clear();
    // Navigate to login
    this.router.navigate(['/login']);
  }

}
