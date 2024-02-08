import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { catchError, Observable, switchMap, throwError } from 'rxjs';
import { AuthenticationService } from 'src/app/sign-in/services/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthenticationService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (localStorage.getItem('accessToken')) {
      const newRequest = req.clone({
        headers: req.headers.set(
          'Authorization',
          `Bearer ${localStorage.getItem('accessToken')}`
        ),
      });

      return next.handle(newRequest).pipe(
        catchError((err) => {
          // CORS miatt kell beletenni a nullás hibát is. ez élesben nem lesz gond, arra csak ki kell szedni
          if (err.status === 403 || err.status === 0) {
            return this.handleRefresh(newRequest, next);
          } else {
            return throwError(() => err);
          }
        })
      );
    }

    return next.handle(req);
  }

  handleRefresh(req: HttpRequest<any>, next: HttpHandler): Observable<any> {
    return this.authService.refresh().pipe(
      switchMap((tokenData) => {
        const newRequest = req.clone({
          headers: req.headers.set(
            'Authorization',
            `Bearer ${tokenData.accessToken}`
          ),
        });
        return next.handle(newRequest);
      })
    );
  }
}
