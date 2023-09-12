import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseholdRowComponent } from './household-row.component';

describe('HouseholdRowComponent', () => {
  let component: HouseholdRowComponent;
  let fixture: ComponentFixture<HouseholdRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HouseholdRowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HouseholdRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
