import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfullVerificationComponent } from './successfull-verification.component';

describe('SuccessfullVerificationComponent', () => {
  let component: SuccessfullVerificationComponent;
  let fixture: ComponentFixture<SuccessfullVerificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessfullVerificationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuccessfullVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
