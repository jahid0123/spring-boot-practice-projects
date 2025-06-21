import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../../../model/class';
import { StorageServiceService } from '../../../core/service/storage-service.service';

@Component({
  selector: 'app-view-book-details',
  imports: [NgIf],
  templateUrl: './view-book-details.component.html',
  styleUrl: './view-book-details.component.css'
})
export class ViewBookDetailsComponent {
    book: any;
books: Book[] = [];

  cart: Book[] = [];
addToCart(book: Book): void {
  const existingBook = this.cart.find(item => item.id === book.id);

  if (existingBook) {
    existingBook.quantity = (existingBook.quantity || 1) + 1;
    alert(`${book.bookName} quantity increased to ${existingBook.quantity}.`);
  } else {
    book.quantity = 1;
    this.cart.push(book);
    alert(`${book.bookName} added to cart.`);
  }

  this.storageService.saveCartItems(this.cart);
}
read() {
  this.router.navigate(['/details']);
}

 
  constructor(private router: Router,
     private storageService: StorageServiceService
  ) {
    const nav = this.router.getCurrentNavigation();
    this.book = nav?.extras?.state?.['book'];
  }

  goBack() {
  history.back();
}
shareWithFriends() {
    if (navigator.share) {
      navigator.share({
        title: 'Check out this book!',
        text: 'I found a great book for you.',
        url: window.location.href
      }).then(() => {
        console.log('Thanks for sharing!');
      }).catch(console.error);
    } else {
      alert('Sharing not supported in this browser.');
    }
  }
}


