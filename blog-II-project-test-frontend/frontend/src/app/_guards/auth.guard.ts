import { AuthenticationService } from 'src/app/sign-in/services/authentication.service';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable, map } from 'rxjs';
import { UserData } from '../sign-in/models/userData.model';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.authService.getUser().pipe(
      map((user) => {
        if (this.checkAccess(route, user)) {
          return true;
        } else {
          this.router.navigate(['/']);
          return false;
        }
      })
    );
  }

  private checkAccess(
    route: ActivatedRouteSnapshot,
    user: UserData | null
  ): boolean {
    const requiresLogin = route.data['requiresLogin'] || false;
    const mustNotBeLoggedIn = route.data['mustNotBeLoggedIn'] || false;
    const requiredRoles = route.data['requiredRoles'] || [];

    const isLoggedIn = !!user;

    if (mustNotBeLoggedIn && isLoggedIn) {
      return false;
    }

    if (requiresLogin) {
      if (isLoggedIn && this.checkRoles(user, requiredRoles)) {
        return true;
      } else {
        return false;
      }
    }

    return true;
  }

  private checkRoles(user: UserData, requiredRoles: string[]): boolean {
    const userRoles = user.role;
    return requiredRoles.every((role) => userRoles.includes(role));
  }
}
