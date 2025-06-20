import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyerRequestComponent } from './buyer-request.component';

describe('BuyerRequestComponent', () => {
  let component: BuyerRequestComponent;
  let fixture: ComponentFixture<BuyerRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuyerRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuyerRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
