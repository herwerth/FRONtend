import { UserData } from 'src/app/sign-in/models/userData.model';
import { AuthenticationService } from './../../../sign-in/services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { faCircleUser } from '@fortawesome/free-regular-svg-icons';
import { Router } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-top-banner',
  templateUrl: './top-banner.component.html',
  styleUrls: ['./top-banner.component.css'],
})
export class TopBannerComponent implements OnInit {
  user!: UserData | null;

  isProfileMenuOpen: boolean = false;

  mobileView!: boolean;

  faCircleUser = faCircleUser;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private breakpointObserver: BreakpointObserver
  ) {}

  ngOnInit(): void {
    this.authService.userObject.subscribe((user) => {
      this.user = user;
    });

    this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result) => {
        if (result.matches) {
          this.mobileView = true;
        } else {
          this.mobileView = false;
        }
      });
  }

  toggleProfileMenu(): void {
    this.isProfileMenuOpen = !this.isProfileMenuOpen;
  }

  signOut(): void {
    this.authService.logout();
    if (this.router.url === '/') {
      location.reload();
    } else {
      this.router.navigate(['']);
    }
  }
}
