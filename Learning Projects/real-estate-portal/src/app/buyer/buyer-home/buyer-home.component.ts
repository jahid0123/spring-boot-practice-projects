import { NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { GetPostedProperty } from '../../model class/user';
import { Router } from '@angular/router';
import { SellerHomeService } from '../../seller/seller-home/service/seller-home.service';
import { BuyerHomeService } from './service/buyer-home.service';

@Component({
  selector: 'app-buyer-home',
  imports: [NgFor],
  templateUrl: './buyer-home.component.html',
  styleUrl: './buyer-home.component.css',
})
export class BuyerHomeComponent implements OnInit {
  selectedProperty: any;
  allPost: GetPostedProperty[] | undefined;

  constructor(
    private router: Router,
    private buyerHomeService: BuyerHomeService
  ) {}

  ngOnInit(): void {
    this.getProperties();
  }

  getProperties() {
    this.buyerHomeService.getAllPost().subscribe({
      next: (res: GetPostedProperty[]) => {
        this.allPost = res;
      },
      error: (err) => {
        console.error(err);
      },
    });
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


  requestInformation(property: any): void {
    //this.selectedProperty = property;
    const userId = Number(localStorage.getItem('id'));
    const propertyPostId = property.id;
    this.buyerHomeService.propertyInfoRequest({userId, propertyPostId}).subscribe({
      next: res=>{
        alert('Request Sent Successfully....');
      },
      error: err=>{
        console.error('Request failed with: ', err);
        alert('Request failed');
      }
    });
  }

  viewDetails(property: any) {
  this.router.navigateByUrl('/buyer-dashboard/property-details', { state: property });
}
}
