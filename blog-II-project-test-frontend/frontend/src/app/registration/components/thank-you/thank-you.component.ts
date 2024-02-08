import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component } from '@angular/core';

@Component({
  selector: 'app-thank-you',
  templateUrl: './thank-you.component.html',
  styleUrls: ['./thank-you.component.css']
})
export class ThankYouComponent {
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
