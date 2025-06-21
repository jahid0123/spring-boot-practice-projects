import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-seeker-dashboard',
  imports: [RouterModule, RouterOutlet],
  templateUrl: './seeker-dashboard.component.html',
  styleUrl: './seeker-dashboard.component.css'
})
export class SeekerDashboardComponent {

  constructor(private router: Router){}



  logout() {
    // Clear local storage or auth token
    localStorage.clear();
    // Navigate to login
    this.router.navigate(['/login']);
  }
}
