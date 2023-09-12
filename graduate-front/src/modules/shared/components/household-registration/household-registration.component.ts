import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { Household } from 'src/modules/shared/model/household';
import { HouseholdService } from 'src/modules/shared/service/household-service/household-service';
import { AddHousehold } from 'src/modules/shared/actions/app.action';
import { Store } from '@ngxs/store';
import { Options } from 'ngx-google-places-autocomplete/objects/options/options';
import { Address } from 'src/modules/shared/model/address';

@Component({
  selector: 'app-household-registration',
  templateUrl: './household-registration.component.html',
  styleUrls: ['./household-registration.component.scss']
})
export class HouseholdRegistrationComponent implements OnInit {
  showSpiner: boolean = false;
  invalidAddress = false;
  misseddAddress = true;
  registrationSubscription: Subscription;
  address: Address = {
    street: '',
    number: '',
    city: '',
    lon: 0,
    lat: 0
  }

  options: Options = new Options({
    bounds: undefined,
    fields: ['address_component', 'formatted_address', 'name', 'geometry'],
    strictBounds: false,
    componentRestrictions: { country: 'rs' },
  });
  
  constructor( 
    private toast: ToastrService, 
    private router: Router,
    private householdService: HouseholdService,
    private store: Store
  ) { 
      this.showSpiner = false;
    }

  ngOnInit(): void {
  }

  registrationForm = new FormGroup(
    {
      nameFormControl: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z -]*'),
      ]),
      regNumberFormControl: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]{8}'),
      ]),
      phoneNumberFormControl: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(12),
        Validators.pattern('[0-9]*'),
      ]),
    }
  );

  isDisabledButton(): boolean {

    return this.registrationForm.invalid;
  }


  register(): void {
    if (this.registrationForm.invalid) {
      this.toast.error('Registration form is invalid.')
    } else {
      const household: Household = {
        name: this.registrationForm.get('nameFormControl')?.value,
        registrationNumber: this.registrationForm.get('regNumberFormControl')?.value,
        phoneNumber: this.registrationForm.get('phoneNumberFormControl')?.value,
        address: this.address
      };

      this.showSpiner = true;
      this.registrationSubscription = this.householdService
            .saveHousehold(household)
            .subscribe(
              response => {
                this.showSpiner = false;
                this.toast.success(
                  'Domaćinstvo je uspešno registrovano.'
                );
                this.store.dispatch(new AddHousehold(response as Household));
                this.router.navigate([`/map/user/products`]);
              },
              error => {
                this.showSpiner = false;
                this.toast.error(error.error, 'Neuspešno')
              }
            );
    }
  }

  addressChange(address: any){
    console.log(address);
    this.invalidAddress = false;
    this.address.street = address.address_components[1].short_name;
    this.address.number = address.address_components[0].short_name;
    this.address.city = address.address_components[2].short_name;
    this.address.lon = address.geometry.location.lng();
    this.address.lat = address.geometry.location.lat();
  }

}
