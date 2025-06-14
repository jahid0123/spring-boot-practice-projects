import { NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-property-details',
  imports: [NgClass, NgFor, NgIf],
  templateUrl: './property-details.component.html',
  styleUrl: './property-details.component.css',
})
export class PropertyDetailsComponent implements OnInit {

  property: any;

  constructor(private location: Location) {}

  ngOnInit(): void {
    const state = history.state;
    if (state) {
      this.property = state;
    }
  }

  goBack() {
    this.location.back();
  }

  saveFavorites(property: any): void {

  const favoritesKey = 'favoriteProperties';

  // Get existing favorites or initialize an empty array
  let favorites = JSON.parse(localStorage.getItem(favoritesKey) || '[]');

  // Check if the property is already saved
  const exists = favorites.some((p: any) => p.title === property.title && p.address === property.address);

  if (!exists) {
    favorites.push(property);
    localStorage.setItem(favoritesKey, JSON.stringify(favorites));
    alert('Property added to favorites!');
  } else {
    alert('Property already in favorites.');
  }
}


  requestInformation(property: any){
    
  }
}
