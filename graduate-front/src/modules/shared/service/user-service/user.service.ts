import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegularUserRegistration } from 'src/modules/auth/model/registration_and_verification/regular-user-registration';
import { VerifyRequest } from 'src/modules/auth/model/registration_and_verification/verify-request';
import { ConfigService } from '../config-service/config.service';
import { Household } from '../../model/household';
import { RegistrationResponse } from 'src/modules/auth/model/registration_and_verification/registration_response';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private configService: ConfigService) { }

  registerRegularUser(newUser: RegularUserRegistration): Observable<RegistrationResponse> {
    return this.http.post<RegistrationResponse>(
      this.configService.CREATE_REGULAR_USER_URL,
      newUser
    );
  }

  verify(verifyRequest: VerifyRequest): Observable<boolean> {
    return this.http.put<boolean>(this.configService.ACTIVATE_ACCOUNT_URL, verifyRequest);
  }

  getUserHousehold(id: number): Observable<Household> {
    return this.http.get<Household>(this.configService.getHouseholdForRegularUserUrl(id));
  }
}
