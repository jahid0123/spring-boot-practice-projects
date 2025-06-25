import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-patient-dashboard',
  imports: [RouterOutlet, RouterModule],
  templateUrl: './patient-dashboard.component.html',
  styleUrl: './patient-dashboard.component.css'
})
export class PatientDashboardComponent {

  constructor(private router: Router){}


  logout() {
  // clear auth token or local storage
  localStorage.clear();
  this.router.navigate(['/login']); // adjust route as needed
}


}
