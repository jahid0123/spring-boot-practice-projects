import { TestBed } from '@angular/core/testing';

import { BuyerRequestService } from './buyer-request.service';

describe('BuyerRequestService', () => {
  let service: BuyerRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuyerRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
