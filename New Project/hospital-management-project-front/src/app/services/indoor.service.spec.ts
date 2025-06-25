import { TestBed } from '@angular/core/testing';

import { IndoorService } from './indoor.service';

describe('IndoorService', () => {
  let service: IndoorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IndoorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
