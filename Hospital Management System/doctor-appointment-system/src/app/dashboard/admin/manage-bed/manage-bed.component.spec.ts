import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageBedComponent } from './manage-bed.component';

describe('ManageBedComponent', () => {
  let component: ManageBedComponent;
  let fixture: ComponentFixture<ManageBedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageBedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageBedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
