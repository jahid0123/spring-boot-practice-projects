import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from './service/user.service';
import { Book } from '../../../model/class';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-dashboard',
  imports: [FormsModule, NgFor],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent {


   books: Book[] = [];

  constructor(private bookService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe((data) => {
      this.books = data;
    });
  }

  addToCart(book: any): void {
  console.log('Adding to cart:', book);
  // Add your cart logic here
}

viewDetails(book: any): void {
  this.router.navigate(['/view-details'], { state: { book } });
}
}
