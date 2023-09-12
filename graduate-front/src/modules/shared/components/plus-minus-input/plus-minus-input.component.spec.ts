import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlusMinusInputComponent } from './plus-minus-input.component';

describe('PlusMinusInputComponent', () => {
  let component: PlusMinusInputComponent;
  let fixture: ComponentFixture<PlusMinusInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlusMinusInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlusMinusInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
