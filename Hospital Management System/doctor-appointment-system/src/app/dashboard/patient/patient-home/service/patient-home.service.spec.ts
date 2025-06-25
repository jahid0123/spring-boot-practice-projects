import { TestBed } from '@angular/core/testing';

import { PatientHomeService } from './patient-home.service';

describe('PatientHomeService', () => {
  let service: PatientHomeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientHomeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
