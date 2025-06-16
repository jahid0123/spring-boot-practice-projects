import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekerHomeComponent } from './seeker-home.component';

describe('SeekerHomeComponent', () => {
  let component: SeekerHomeComponent;
  let fixture: ComponentFixture<SeekerHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeekerHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeekerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
