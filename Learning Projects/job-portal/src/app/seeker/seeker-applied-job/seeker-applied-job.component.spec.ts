import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekerAppliedJobComponent } from './seeker-applied-job.component';

describe('SeekerAppliedJobComponent', () => {
  let component: SeekerAppliedJobComponent;
  let fixture: ComponentFixture<SeekerAppliedJobComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeekerAppliedJobComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeekerAppliedJobComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
