import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegularUserRegistration } from 'src/modules/auth/model/registration_and_verification/regular-user-registration';
import { VerifyRequest } from 'src/modules/auth/model/registration_and_verification/verify-request';
import { ConfigService } from 'src/modules/shared/service/config-service/config.service';
import { Household } from 'src/modules/shared/model/household';
import { AuthService } from 'src/modules/auth/service/auth/auth.service';
import { Search } from '../../model/search';

@Injectable({
  providedIn: 'root'
})
export class HouseholdService {

  constructor(private http: HttpClient, private configService: ConfigService, private authService: AuthService) { }

  getOffersForSearch(search: Search){
    return this.http.post(this.configService.HOUSEHOLD_OFFERS_SEARCH_URL, search);
  }

  saveHousehold(household: Household){
    const user = this.authService.getLoggedParsedUser();
    household.userId = user.id;
    return this.http.post<Household>(this.configService.HOUSEHOLD_URL, household);
  }

  getHouseholdsWithPagination(pageNumber: number, pageSize: number): Observable<Household[]>{
    return this.http.get<Household[]>(this.configService.getHouseholdsUrl(pageNumber, pageSize));
  }

  getHousehold(id: number): Observable<Household> {
    return this.http.get<Household>(this.configService.getHouseholdUrl(id));
  }
}