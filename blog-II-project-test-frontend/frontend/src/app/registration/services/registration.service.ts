import { Injectable } from '@angular/core';
import { UserModel } from '../models/user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/config';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  BASE_URL = BASE_URL;

  constructor(private http: HttpClient) {}

  saveUser(user: UserModel): Observable<UserModel> {
    let header: HttpHeaders = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=UTF-8'
    );

    return this.http.post<UserModel>(this.BASE_URL + 'accounts/save', user, {
      headers: header,
    });
  }
}
