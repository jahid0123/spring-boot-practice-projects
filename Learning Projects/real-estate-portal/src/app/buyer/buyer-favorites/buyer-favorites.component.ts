import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { GetPostedProperty } from '../../model class/user';

@Component({
  selector: 'app-buyer-favorites',
  imports: [ReactiveFormsModule, CommonModule, ],
  templateUrl: './buyer-favorites.component.html',
  styleUrl: './buyer-favorites.component.css'
})
export class BuyerFavoritesComponent  implements OnInit {
  favoriteProperties: any[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.loadFavorites();
  }

  loadFavorites(): void {
    const data = localStorage.getItem('favoriteProperties');
    this.favoriteProperties = data ? JSON.parse(data) : [];
  }

  removeFromFavorites(property: any): void {
    this.favoriteProperties = this.favoriteProperties.filter(p => p.id !== property.id);
    localStorage.setItem('favoriteProperties', JSON.stringify(this.favoriteProperties));
  }

  viewDetails(property: GetPostedProperty): void {
    this.router.navigateByUrl('/buyer-dashboard/property-details', { state: { property } });
  }
}