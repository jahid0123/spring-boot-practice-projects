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

export interface AddBook {
  bookName: string;
  bookIsbnNumber: number;
  bookPrice: number;        // BigDecimal maps to number in TypeScript
  bookRating: number;
  bookCategory: string;
  bookQuantity: number;
  bookAuthorIds: number[];  // Set<Long> becomes number[] in TypeScript
}
