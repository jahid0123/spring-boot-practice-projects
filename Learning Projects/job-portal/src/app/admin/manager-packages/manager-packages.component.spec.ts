import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerPackagesComponent } from './manager-packages.component';

describe('ManagerPackagesComponent', () => {
  let component: ManagerPackagesComponent;
  let fixture: ComponentFixture<ManagerPackagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagerPackagesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagerPackagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
