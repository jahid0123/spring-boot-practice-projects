import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-doctor-dashboard',
  imports: [RouterOutlet, RouterModule],
  templateUrl: './doctor-dashboard.component.html',
  styleUrl: './doctor-dashboard.component.css'
})
export class DoctorDashboardComponent {


  constructor(private router: Router){}


   logout() {
  localStorage.clear();
  this.router.navigate(['/login']);
}

}
