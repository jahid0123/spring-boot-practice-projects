import { Component, OnInit } from '@angular/core';
import { StorageServiceService } from '../../../core/service/storage-service.service';
import { Book } from '../../../model/class';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-favourite',
  imports: [NgFor, NgIf],
  templateUrl: './favourite.component.html',
  styleUrl: './favourite.component.css'
})
export class FavouriteComponent implements OnInit {
  favoriteBooks: any[] = [];

  ngOnInit(): void {
    const storedFavorites = localStorage.getItem('favorites');
    this.favoriteBooks = storedFavorites ? JSON.parse(storedFavorites) : [];
  }

  removeFromFavorites(bookId: number): void {
    this.favoriteBooks = this.favoriteBooks.filter(b => b.id !== bookId);
    localStorage.setItem('favorites', JSON.stringify(this.favoriteBooks));
  }
}
