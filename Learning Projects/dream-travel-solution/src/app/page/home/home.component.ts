import { Component, OnInit } from '@angular/core';
import { Package } from '../../model.class/class';
import { HomeService } from './service/home.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../authcomponent/service/auth.service';

@Component({
  selector: 'app-home',
  imports: [RouterModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
 packages: Package[] = [];
  role: string | null = null;
  errorMessage: string | null = null;

  constructor(private packageService: HomeService, private authService: AuthService) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
    this.packageService.getAllPackages().subscribe({
      next: (data: Package[]) => {
        this.packages = data;
        this.errorMessage = null;
      },
      error: (err: any) => {
        this.errorMessage = 'Failed to load packages. Please try again later.';
        console.error('Error fetching packages:', err);
      }
    });
  }

  handleImageError(event: Event): void {
    const imgElement = event.target as HTMLImageElement;
    imgElement.src = 'https://via.placeholder.com/200x200?text=No+Image';
  }
}
