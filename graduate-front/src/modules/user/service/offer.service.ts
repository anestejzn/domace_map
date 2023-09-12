import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegularUserRegistration } from 'src/modules/auth/model/registration_and_verification/regular-user-registration';
import { VerifyRequest } from 'src/modules/auth/model/registration_and_verification/verify-request';
import { ConfigService } from 'src/modules/shared/service/config-service/config.service';
import { Household } from 'src/modules/shared/model/household';
import { AuthService } from 'src/modules/auth/service/auth/auth.service';
import { Offer } from 'src/modules/shared/model/offer';
import { AppState } from 'src/modules/shared/state/app.state';
import { Select } from '@ngxs/store';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  constructor(private http: HttpClient, private configService: ConfigService) { }

  saveOffer(offer: Offer){
    return this.http.post(this.configService.OFFER_URL, offer);
  }

  updateOffer(offer: Offer){
    return this.http.put(this.configService.getUpdateOfferUrl(offer.id), offer);
  }

  getOffersWithPagination(pageNumber: number, pageSize: number, householdId: number): Observable<Offer[]>{
    return this.http.get(`${this.configService.getOffersUrl(householdId)}/${pageNumber}/${pageSize}`) as Observable<Offer[]>;
  }

  deleteOffer(id: number){
    return this.http.delete(this.configService.getDeleteOfferUrl(id));
  }
}
