import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Observable, Subscription } from 'rxjs';
import { matchPasswordsValidator } from 'src/modules/shared/validators/confirm-password.validator';
import { ToastrService } from 'ngx-toastr'
import { Router } from '@angular/router';
import { UserService } from 'src/modules/shared/service/user-service/user.service';
import { RegularUserRegistration } from '../../model/registration_and_verification/regular-user-registration';
import { RegistrationResponse } from '../../model/registration_and_verification/registration_response';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit, OnDestroy {

  showSpiner: boolean = false;
  hidePassword = true;
  hideConfirmPassword = true;
  
  filteredCities: Observable<string[]> = new Observable();
  registrationSubscription: Subscription;
  
  constructor(
    private toast: ToastrService, 
    private router: Router,
    private userService: UserService
  ) {
    // this.filteredCities = this.registrationForm
    //   .get('cityFormControl')
    //   .valueChanges.pipe(
    //     startWith(''),
    //     map(city => (city ? this._filterCities(city) : this.cities.slice()))
    //   );

    //   this.filteredCountries = this.registrationForm.get('countryFormControl')?.valueChanges.pipe(
    //     startWith(''),
    //     map((country: string) => (country ? this._filterCountries(country) : this.countries.slice()))
    //   );  
    this.showSpiner = false;
  }

  ngOnInit(): void {}

  registrationForm = new FormGroup(
    {
      emailFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      nameFormControl: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z -]*'),
      ]),
      surnameFormControl: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z -]*'),
      ]),
      passwordAgainFormControl: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,100}$')
      ]),
      passwordFormControl: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,100}$')
      ])
    }, [matchPasswordsValidator()]
  );

  matcher = new MyErrorStateMatcher();

  register(): void {
    if (this.registrationForm.hasError('mismatch')) {
      this.toast.error('Passwords not match');
    } else if (this.registrationForm.invalid) {
      this.toast.error('Registration form is invalid.')
    } else {
      const newUser: RegularUserRegistration = {
        email: this.registrationForm.get('emailFormControl')?.value ?? '',
        name: this.registrationForm.get('nameFormControl')?.value ?? '',
        surname: this.registrationForm.get('surnameFormControl')?.value ?? '',
        password: this.registrationForm.get('passwordFormControl')?.value ?? '',
        confirmPassword: this.registrationForm.get('passwordAgainFormControl')?.value ?? '',
      }

      this.showSpiner = true;
      this.registrationSubscription = this.userService
            .registerRegularUser(newUser)
            .subscribe(
              (response: RegistrationResponse) => {
                this.showSpiner = false;
                this.toast.success(
                  'Uspešna registracija!'
                );
                this.router.navigate([`/map/auth/verify`, response.verificationId]);
              },
              error => {
                this.showSpiner = false;
                this.toast.error(error.error, 'Registration failed')
              }
            );
    }
  }

  getError() {
    return this.registrationForm.hasError('mismatch');
  }

  ngOnDestroy(): void {
    if (this.registrationSubscription) {
      this.registrationSubscription.unsubscribe();
    }  
  }

}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(
    control: FormControl | null,
    form: FormGroupDirective | NgForm | null
  ): boolean {
    const isSubmitted = form && form.submitted;
    return !!(
      control &&
      control.invalid &&
      (control.dirty || control.touched || isSubmitted)
    );
  }
}
