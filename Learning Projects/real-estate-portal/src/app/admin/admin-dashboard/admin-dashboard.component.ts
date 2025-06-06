import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  imports: [RouterModule, NgIf],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {

  username: string = 'Admin User'; // Replace with actual user name from service
  userAvatarUrl: string = 'https://i.pravatar.cc/40?img=68'; // Example avatar, replace with dynamic URL
  newNotificationsCount: number = 3; // Example notification count, replace with dynamic data

  constructor(private router: Router) { }

  ngOnInit(): void {
    // You might fetch user details or notification counts here
  }

  logout(): void {
    // Implement your logout logic here
    console.log('Admin user logged out!');
    // Example: redirect to login page
    this.router.navigate(['/auth/admin-login']);
    // You'll likely clear auth tokens, user data from local storage/session here
  }
}