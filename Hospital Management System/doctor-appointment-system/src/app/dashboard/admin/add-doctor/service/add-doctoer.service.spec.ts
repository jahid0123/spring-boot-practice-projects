import { TestBed } from '@angular/core/testing';

import { AddDoctoerService } from './add-doctoer.service';

describe('AddDoctoerService', () => {
  let service: AddDoctoerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddDoctoerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
