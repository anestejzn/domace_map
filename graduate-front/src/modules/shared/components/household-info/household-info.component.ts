import { Component, Input, OnInit } from '@angular/core';
import { Household } from '../../model/household';

@Component({
  selector: 'app-household-info',
  templateUrl: './household-info.component.html',
  styleUrls: ['./household-info.component.scss']
})
export class HouseholdInfoComponent implements OnInit {
  @Input() storedHousehold: Household;

  constructor() { }

  ngOnInit(): void {
  }

}
