import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './sign-in/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'frontend';

  constructor(private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.authService.getUser().subscribe();
  }
}
