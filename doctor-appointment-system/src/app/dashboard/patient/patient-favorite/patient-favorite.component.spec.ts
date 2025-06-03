import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientFavoriteComponent } from './patient-favorite.component';

describe('PatientFavoriteComponent', () => {
  let component: PatientFavoriteComponent;
  let fixture: ComponentFixture<PatientFavoriteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientFavoriteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatientFavoriteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
