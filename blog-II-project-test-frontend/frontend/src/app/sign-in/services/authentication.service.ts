import { BASE_URL } from '../../config';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, tap } from 'rxjs';
import { AuthResponse } from '../models/authResponse.model';
import { UserData } from '../models/userData.model';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  BASE_URL = BASE_URL;

  private _userObject = new BehaviorSubject<UserData | null>(null);

  constructor(private http: HttpClient) {}

  login(credential: Credential): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(this.BASE_URL + 'accounts/login', credential)
      .pipe(
        tap((loginData) => {
          if (loginData.accessToken && loginData.refreshToken) {
            localStorage.setItem('accessToken', loginData.accessToken);
            localStorage.setItem('refreshToken', loginData.refreshToken);
          }
          this.getUser().subscribe();
        })
      );
  }

  refresh(): Observable<{ accessToken: string }> {
    const refreshToken = localStorage.getItem('refreshToken');
    return this.http
      .post<{ accessToken: string }>(this.BASE_URL + 'accounts/token/refresh', {
        refreshToken,
      })
      .pipe(
        tap((tokenData) => {
          if (tokenData.accessToken) {
            localStorage.setItem('accessToken', tokenData.accessToken);
          }
        })
      );
  }

  logout(): void {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    this._userObject.next(null);
  }

  getUser(): Observable<UserData | null> {
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');

    if (accessToken && refreshToken) {
      return this.http.get<UserData>(this.BASE_URL + 'accounts/me').pipe(
        tap((userData) => {
          this._userObject.next(userData);
        })
      );
    } else {
      return of(null);
    }
  }

  get userObject(): BehaviorSubject<UserData | null> {
    return this._userObject;
  }
}
