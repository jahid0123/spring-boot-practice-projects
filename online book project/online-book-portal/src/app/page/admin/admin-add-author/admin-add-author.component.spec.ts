import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddAuthorComponent } from './admin-add-author.component';

describe('AdminAddAuthorComponent', () => {
  let component: AdminAddAuthorComponent;
  let fixture: ComponentFixture<AdminAddAuthorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminAddAuthorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddAuthorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
