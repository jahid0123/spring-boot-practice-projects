import { TestBed } from '@angular/core/testing';

import { BuyerFavoritesService } from './buyer-favorites.service';

describe('BuyerFavoritesService', () => {
  let service: BuyerFavoritesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuyerFavoritesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
