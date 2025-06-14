import { Component, Input } from '@angular/core';
import { PropertyInfoResponseDto } from '../../model class/user';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-buyer-request',
  imports: [NgClass],
  templateUrl: './buyer-request.component.html',
  styleUrl: './buyer-request.component.css'
})
export class BuyerRequestComponent {


   @Input() property!: PropertyInfoResponseDto;

  saveToFavorites(property: PropertyInfoResponseDto) {
    // your localStorage saving logic
  }

  viewDetails(property: PropertyInfoResponseDto) {
    // your navigation logic
  }
}

