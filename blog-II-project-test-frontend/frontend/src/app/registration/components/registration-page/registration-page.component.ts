import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component } from '@angular/core';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent {
  showBanner!: boolean;

  constructor(private breakpointObserver: BreakpointObserver) {
    breakpointObserver
      .observe([Breakpoints.Large, Breakpoints.Medium, '(min-width: 576px)'])
      .subscribe((result) => {
        if (result.matches) {
          this.showBanner = true;
        } else {
          this.showBanner = false;
        }
      });
  }
}
