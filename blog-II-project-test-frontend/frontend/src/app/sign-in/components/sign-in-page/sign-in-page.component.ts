import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component } from '@angular/core';

@Component({
  selector: 'app-sign-in-page',
  templateUrl: './sign-in-page.component.html',
  styleUrls: ['./sign-in-page.component.css'],
})
export class SignInPageComponent {
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
