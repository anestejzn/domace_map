import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-successfull-verification',
  templateUrl: './successfull-verification.component.html',
  styleUrls: ['./successfull-verification.component.css']
})
export class SuccessfullVerificationComponent {

  constructor(private router: Router) { }

  redirectToLogin() {
    this.router.navigate(['/map/auth/login']);
  }
}
