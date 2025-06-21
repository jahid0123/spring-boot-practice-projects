import { Injectable } from '@angular/core';
import { Book } from '../../model/class';

@Injectable({
  providedIn: 'root'
})
export class StorageServiceService {
private cartKey = 'cartItems';
  private favoritesKey = 'favoriteItems';

  // CART
  getCartItems(): Book[] {
    return JSON.parse(localStorage.getItem(this.cartKey) || '[]');
  }

  saveCartItems(items: Book[]): void {
    localStorage.setItem(this.cartKey, JSON.stringify(items));
  }

  // FAVORITES
  getFavoriteItems(): Book[] {
    return JSON.parse(localStorage.getItem(this.favoritesKey) || '[]');
  }

  saveFavoriteItems(items: Book[]): void {
    localStorage.setItem(this.favoritesKey, JSON.stringify(items));
  }

  clearCart(): void {
  localStorage.removeItem(this.cartKey);
}

clearFavorites(): void {
  localStorage.removeItem(this.favoritesKey);
}
}
