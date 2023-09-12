import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {


  constructor() { }

  API_URL = environment.apiUrl;
  BASE64_PHOTO_PREFIX = 'data:image/png;base64,';
  ///////////////////AUTH///////////////////
  AUTH_URL = `${this.API_URL}/auth`;
  LOGIN_URL = `${this.AUTH_URL}/login`;
  LOGOUT_URL = `${this.AUTH_URL}/logout`;
  CONFIRM_PIN_URL = `${this.AUTH_URL}/confirm-pin`;

  getLoginUrl(): string {
    return this.LOGIN_URL;
  }

  getLogoutUrl(): string {
    return this.LOGOUT_URL;
  }

  //////////////////USERS////////////////
  USERS_URL = `${this.API_URL}/users`;
  CREATE_REGULAR_USER_URL = `${this.USERS_URL}/register`;
  ACTIVATE_ACCOUNT_URL = `${this.USERS_URL}/activate-account`;

  /////////////////VERIFY/////////////////
  VERIFY_URL = `${this.API_URL}/verify`;
  SEND_CODE_AGAIN_URL = `${this.VERIFY_URL}/send-code-again`;

  ////////////REGULAR USER//////////////////
  REGULAR_USERS_URL = `${this.API_URL}/regular-users`;
  getHouseholdForRegularUserUrl(userId: number): string {
    return `${this.REGULAR_USERS_URL}/get-household/${userId}`;
  }

  /////////HOUSEHOLD//////
  HOUSEHOLD_URL = `${this.API_URL}/household`;
  HOUSEHOLD_OFFERS_SEARCH_URL = `${this.HOUSEHOLD_URL}/search`;

  getHouseholdsUrl(pageNumber: number, pageSize: number) {
    return `${this.HOUSEHOLD_URL}/${pageNumber}/${pageSize}`;
  }

  getHouseholdUrl(id: number) {
    return `${this.HOUSEHOLD_URL}/${id}`;
  }

  //////////OFFER//////
  OFFER_URL =  `${this.API_URL}/offer`;
  getOffersUrl(householdId: number) {
    return `${this.OFFER_URL}/${householdId}`;
  }

  getUpdateOfferUrl(id: number) {
    return `${this.OFFER_URL}/update/${id}`;
  }

  getDeleteOfferUrl(id: number) {
    return `${this.OFFER_URL}/${id}`;
  }

  //////////ORDER//////
  ORDER_URL =  `${this.API_URL}/order`;
  REJECT_ORDER_URL = `${this.ORDER_URL}/reject`;

  getOrdersUrl(forHousehold: boolean, id: number) {
    return `${this.ORDER_URL}/${forHousehold}/${id}`;
  }

  getOrderUrl(id: number) {
    return `${this.ORDER_URL}/${id}`;
  }

  getMarkAsSentOrderUrl(id: number) {
    return `${this.ORDER_URL}/sent/${id}`;
  }

  getMarkAsDeliveredOrderUrl(id: number) {
    return `${this.ORDER_URL}/delivered/${id}`;
  }

  ////////NOTIFICATION//////
  NOTIFICATION_URL =  `${this.API_URL}/notification`;
  getNotificationsForUserUrl(userId:number): string {

    return `${this.NOTIFICATION_URL}/${userId}`;
  }

  getReadNotificationsForUserUrl(userId:number): string {

    return `${this.NOTIFICATION_URL}/read/${userId}`;
  }

  ///////REVIEW/////////
  REVIEW_URL =  `${this.API_URL}/review`;
  getReviewsForOfferIdUrl(id: number) {
    return `${this.REVIEW_URL}/${id}`;
  }

  deleteReviewUrl(id: number, offerId: number) {
    return `${this.REVIEW_URL}/${id}/${offerId}`;
  }

  getReviewsUrl(pageNumber: number, pageSize: number) {
    return `${this.REVIEW_URL}/${pageNumber}/${pageSize}`;
  }

}
