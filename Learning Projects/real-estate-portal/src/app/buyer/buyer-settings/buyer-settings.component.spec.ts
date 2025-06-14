import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyerSettingsComponent } from './buyer-settings.component';

describe('BuyerSettingsComponent', () => {
  let component: BuyerSettingsComponent;
  let fixture: ComponentFixture<BuyerSettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuyerSettingsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuyerSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
