import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetingRequestComponent } from './meeting-request.component';

describe('MeetingRequestComponent', () => {
  let component: MeetingRequestComponent;
  let fixture: ComponentFixture<MeetingRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MeetingRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MeetingRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
