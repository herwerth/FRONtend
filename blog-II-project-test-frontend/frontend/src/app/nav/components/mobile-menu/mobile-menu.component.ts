import { Component, OnInit } from '@angular/core';
import {
  faFacebookF,
  faXTwitter,
  faYoutube,
} from '@fortawesome/free-brands-svg-icons';
import { Observable } from 'rxjs';
import { UserData } from 'src/app/sign-in/models/userData.model';
import { AuthenticationService } from 'src/app/sign-in/services/authentication.service';

@Component({
  selector: 'app-mobile-menu',
  templateUrl: './mobile-menu.component.html',
  styleUrls: ['./mobile-menu.component.css'],
})
export class MobileMenuComponent implements OnInit {
  user$!: Observable<UserData | null>;
  
  faFacebookF = faFacebookF;
  faXTwitter = faXTwitter;
  faYoutube = faYoutube;

  constructor(private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.user$ = this.authService.userObject;
  }
}
