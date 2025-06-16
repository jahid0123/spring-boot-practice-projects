import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-sidebar',
  imports: [RouterModule],
  templateUrl: './admin-sidebar.component.html',
  styleUrl: './admin-sidebar.component.css',
})
export class AdminSidebarComponent {
  constructor(private router: Router) {}

  logout() {
    // Clear local storage or auth token
    localStorage.clear();
    // Navigate to login
    this.router.navigate(['/login']);
  }
}
