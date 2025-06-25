import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmitedPatientComponent } from './admited-patient.component';

describe('AdmitedPatientComponent', () => {
  let component: AdmitedPatientComponent;
  let fixture: ComponentFixture<AdmitedPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmitedPatientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmitedPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
