import { TestBed } from '@angular/core/testing';

import { BuyerSettingsService } from './buyer-settings.service';

describe('BuyerSettingsService', () => {
  let service: BuyerSettingsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuyerSettingsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
