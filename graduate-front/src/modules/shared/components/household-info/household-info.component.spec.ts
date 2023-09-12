import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseholdInfoComponent } from './household-info.component';

describe('HouseholdInfoComponent', () => {
  let component: HouseholdInfoComponent;
  let fixture: ComponentFixture<HouseholdInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HouseholdInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HouseholdInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
