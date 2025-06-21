import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekerSettingsComponent } from './seeker-settings.component';

describe('SeekerSettingsComponent', () => {
  let component: SeekerSettingsComponent;
  let fixture: ComponentFixture<SeekerSettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeekerSettingsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeekerSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
