import { Component } from '@angular/core';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { NavStatusService } from '../../services/nav-status.service';

@Component({
  selector: 'app-navigation-menu',
  templateUrl: './navigation-menu.component.html',
  styleUrls: ['./navigation-menu.component.css'],
  animations: [
    trigger('toggleNav', [
      state(
        'open',
        style({
          transform: 'translate(16rem)',
          width: 'calc(100% - 16rem)',
        })
      ),
      state(
        'closed',
        style({
          transform: 'translate(0)',
          width: '100%',
        })
      ),
      transition('open => closed', [animate('0.15s')]),
      transition('closed => open', [animate('0.15s')]),
    ]),
    trigger('showNav', [
      state(
        'open',
        style({
          transform: 'translate(0)',
          width: '16rem',
        })
      ),
      state(
        'closed',
        style({
          transform: 'translate(-16rem)',
          width: '0',
        })
      ),
      transition('open => closed', [animate('0.15s')]),
      transition('closed => open', [animate('0.15s')]),
    ]),
  ],
})
export class NavigationMenuComponent {
  title = 'frontend';

  isMobileNavVisible: boolean = false;
  webView: boolean = false;

  faClose = faXmark;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private navStatus: NavStatusService
  ) {
    this.breakpointObserver
      .observe([Breakpoints.XLarge, Breakpoints.Large, Breakpoints.Medium])
      .subscribe((result) => {
        if (result.matches) {
          this.webView = true;
          this.isMobileNavVisible = false;
        } else {
          this.webView = false;
        }
      });
  }

  ngOnInit() {
    this.navStatus
      .getState()
      .subscribe((state) => (this.isMobileNavVisible = state));
  }

  toggleIsMobileNavVisible() {
    this.navStatus.toggleState();
  }
}
