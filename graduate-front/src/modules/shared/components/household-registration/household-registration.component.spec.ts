import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseholdRegistrationComponent } from './household-registration.component';

describe('HouseholdRegistrationComponent', () => {
  let component: HouseholdRegistrationComponent;
  let fixture: ComponentFixture<HouseholdRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HouseholdRegistrationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HouseholdRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
