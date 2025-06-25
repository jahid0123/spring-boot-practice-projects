import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmissionPatientComponent } from './admission-patient.component';

describe('AdmissionPatientComponent', () => {
  let component: AdmissionPatientComponent;
  let fixture: ComponentFixture<AdmissionPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmissionPatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmissionPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
