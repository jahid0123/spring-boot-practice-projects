import { Component } from '@angular/core';
import { Book } from '../../../model/class';
import { UserService } from '../user-dashboard/service/user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-user-home',
  imports: [FormsModule, NgFor],
  templateUrl: './user-home.component.html',
  styleUrl: './user-home.component.css'
})
export class UserHomeComponent {

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
