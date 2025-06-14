import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { GetPostedProperty } from '../../model class/user';
import { Route, Router } from '@angular/router';
import { SellerHomeService } from './service/seller-home.service';

@Component({
  selector: 'app-seller-home',
  imports: [NgFor, NgIf],
  templateUrl: './seller-home.component.html',
  styleUrl: './seller-home.component.css',
})
export class SellerHomeComponent implements OnInit {
  allPost: GetPostedProperty[] | undefined;

  constructor(
    private router: Router,
    private sellerHomeService: SellerHomeService
  ) {}

  ngOnInit(): void {
    this.getProperties();
  }

  getProperties() {
    this.sellerHomeService.getAllPost().subscribe({
      next: (res: GetPostedProperty[]) => {
        this.allPost = res;
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  editProperty(property: GetPostedProperty) {
    this.router.navigate(['/seller-dashboard/add-property'], {
      state: { property: property },
    });
  }
  deleteProperty(id: number) {
    this.sellerHomeService.deletePost(id).subscribe({
      next: (res) => {
        alert('Delete Successfully..');
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
}
