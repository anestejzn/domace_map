import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Household } from 'src/modules/shared/model/household';

@Component({
  selector: 'app-household-row',
  templateUrl: './household-row.component.html',
  styleUrls: ['./household-row.component.scss']
})
export class HouseholdRowComponent implements OnInit {
  @Input() household: Household;
  @Input() index: number;

  constructor(private router: Router) {}

  ngOnInit(): void {}

  navigateToHousehold(id: number) {
    this.router.navigate(['/map/household', id]);
  }

}
