import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/modules/shared/model/user';
import { ConfigService } from 'src/modules/shared/service/config-service/config.service';
import { VerifyRequest } from '../../model/registration_and_verification/verify-request';


@Injectable({
  providedIn: 'root',
})
export class VerifyService {
  constructor(
    private http: HttpClient,
    private configService: ConfigService
  ) {}

  verify(verifyRequest: VerifyRequest): Observable<User> {
    return this.http.put<User>(this.configService.ACTIVATE_ACCOUNT_URL, verifyRequest);
  }

  sendCodeAgain(verifyId: number): Observable<User> {
    return this.http.post<User>(
      this.configService.SEND_CODE_AGAIN_URL,
      verifyId
    );
  }

  createVerifyRequest(verifyId: string, securityCode: number): VerifyRequest {
    return {
      verifyId: verifyId,
      securityCode: securityCode,
    };
  }
}
