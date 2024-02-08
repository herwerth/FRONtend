import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
  HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/app/config';
import { ChangeUserData } from '../models/changeUserData.model';
import { ChangePasswordData } from '../models/changePasswordData.model';
import { Observable, catchError, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ChangeUserDataService {
  BASE_URL = BASE_URL;
  constructor(private http: HttpClient) {}

  header: HttpHeaders = new HttpHeaders().set(
    'Content-Type',
    'application/json; charset=UTF-8'
  );

  saveUserData(userData: ChangeUserData): Observable<any> {
    return this.http.put(this.BASE_URL + 'accounts/update', userData, {
      headers: this.header,
    });
  }

  changePassword(data: ChangePasswordData): Observable<any> {
    return this.http
      .put(this.BASE_URL + 'accounts/changepassword', data, {
        observe: 'response',
      })
      .pipe(
        catchError((errorResponse) => this.handleChangePwError(errorResponse))
      );
  }

  handleChangePwError(response: any): any {
    if (response.status === 200) {
      if (response.error && response.error.text) {
        const responseText = response.error.text;
        return of(responseText);
      }
    } else {
      return throwError(response);
    }
  }
}
