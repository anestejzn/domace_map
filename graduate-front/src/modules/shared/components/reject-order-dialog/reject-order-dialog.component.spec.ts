import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectOrderDialogComponent } from './reject-order-dialog.component';

describe('RejectOrderDialogComponent', () => {
  let component: RejectOrderDialogComponent;
  let fixture: ComponentFixture<RejectOrderDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RejectOrderDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RejectOrderDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
