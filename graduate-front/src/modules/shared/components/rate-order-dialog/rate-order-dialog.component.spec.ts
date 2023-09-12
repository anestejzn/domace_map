import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateOrderDialogComponent } from './rate-order-dialog.component';

describe('RateOrderDialogComponent', () => {
  let component: RateOrderDialogComponent;
  let fixture: ComponentFixture<RateOrderDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateOrderDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RateOrderDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
