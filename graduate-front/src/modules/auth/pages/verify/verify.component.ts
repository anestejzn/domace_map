import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { VerifyService } from '../../service/verify/verify.service';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css'],
})
export class VerifyComponent implements OnInit, OnDestroy {
  firstDigit: string;
  secondDigit: string;
  thirdDigit: string;
  fourthDigit: string;
  verifyId: string;
  showForm = true;
  MAX_DIGIT_LENGTH = 4;

  verifySubscription: Subscription;
  sendCodeAgainSubscription: Subscription;

  constructor(
    private verifyService: VerifyService,
    private route: ActivatedRoute,
    private toast: ToastrService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.verifyId = this.route.snapshot.paramMap.get('id');
  }

  containsOnlyNumbers(str: string) {
    return /^\d+$/.test(str);
  }

  checkValidationCode(): boolean {
    const securityCode: string =
      this.firstDigit + this.secondDigit + this.thirdDigit + this.fourthDigit;
    if (securityCode.length !== this.MAX_DIGIT_LENGTH) {
      this.toast.error('You need to add 4 digits.', 'Error');

      return false;
    } else if (!this.containsOnlyNumbers(securityCode)) {
      this.toast.error('You can input only digits!', 'Error');

      return false;
    } 

    return true;
  }

  verify() {
    if (this.checkValidationCode()) {
      const securityCode: string =
        this.firstDigit + this.secondDigit + this.thirdDigit + this.fourthDigit;

      this.verifySubscription = this.verifyService.verify(
          this.verifyService.createVerifyRequest(
            this.verifyId,
            +securityCode
        ))
        .subscribe(
          res => {
            this.router.navigate(['/map/auth/successfull-verification']);
          },
          error => this.toast.error(error.error, 'Verification failed')
        );
    }
  }

  sendCodeAgain() {
    if (this.containsOnlyNumbers(this.verifyId)) {
      this.sendCodeAgainSubscription = this.verifyService
        .sendCodeAgain(Number(this.verifyId))
        .subscribe(
          res => (this.showForm = !this.showForm),
          error =>{
            this.toast.error('Email cannot be sent.', 'Code cannot be sent');
          }
        );
    } else {
      this.toast.error(
        'Something happened with URL. Check again URL on email.',
        'Code cannot be sent'
      );
    }
  }

  goToHomePage(){
    this.router.navigate(["/serb-uber/user/map-page-view/-1"]);
  }

  ngOnDestroy(): void {
    if (this.verifySubscription) {
      this.verifySubscription.unsubscribe();
    }

    if (this.sendCodeAgainSubscription)
      this.sendCodeAgainSubscription.unsubscribe();
  }

  goToLoginPage(){
    this.router.navigate(["/map/auth/login"]);
  }
}
