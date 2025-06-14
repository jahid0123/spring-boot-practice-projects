import { TestBed } from '@angular/core/testing';

import { SellerHomeService } from './seller-home.service';

describe('SellerHomeService', () => {
  let service: SellerHomeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SellerHomeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
