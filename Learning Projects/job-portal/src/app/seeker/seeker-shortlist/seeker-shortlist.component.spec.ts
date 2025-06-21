import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekerShortlistComponent } from './seeker-shortlist.component';

describe('SeekerShortlistComponent', () => {
  let component: SeekerShortlistComponent;
  let fixture: ComponentFixture<SeekerShortlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeekerShortlistComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeekerShortlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
