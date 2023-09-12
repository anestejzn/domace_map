import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutocompleteMapInputComponent } from './autocomplete-map-input.component';

describe('AutocompleteMapInputComponent', () => {
  let component: AutocompleteMapInputComponent;
  let fixture: ComponentFixture<AutocompleteMapInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutocompleteMapInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutocompleteMapInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
