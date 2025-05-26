export interface Book {
  id: number;
  bookName: string;
  bookIsbnNumber: number;
  bookPrice: number;
  bookRating: number;
  bookCategory: string;
  bookQuantity: number;
  createdAt: string;
}

// src/app/models/book.model.ts

// src/app/models/book.model.ts

export class AddBook {
  id: number = 0;
  bookName: string = '';
  bookIsbnNumber: number = 0;
  bookPrice: number = 0;
  bookRating: number = 0;
  bookCategory: string = '';
  bookQuantity: number = 1;
  bookAuthorIds: number[] = [];

  // Optional helper for template input (e.g., comma-separated authors)
  bookAuthorIdsString: string = '';

  constructor(init?: Partial<Book>) {
    Object.assign(this, init);
  }

  // Converts comma-separated string into number array
  parseAuthorIds() {
    this.bookAuthorIds = this.bookAuthorIdsString
      .split(',')
      .map(id => +id.trim())
      .filter(id => !isNaN(id));
  }

  // Converts number array back into comma-separated string
  formatAuthorIds() {
    this.bookAuthorIdsString = this.bookAuthorIds.join(', ');
  }
}

export interface OrderResponse {
  orderId: number;
  orderPrice: number;
  userId: number;
  bookIds: number[];
  createdAt: string;
}