import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  faMagnifyingGlass,
  faXmark,
  faBars,
} from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { NavStatusService } from 'src/app/nav/services/nav-status.service';
import { SearchbarStatusService } from 'src/app/nav/services/searchbar-status.service';

@Component({
  selector: 'app-mobile-header',
  templateUrl: './mobile-header.component.html',
  styleUrls: ['./mobile-header.component.css'],
})
export class MobileHeaderComponent implements OnInit, OnDestroy {
  navbarSubs: Subscription = new Subscription();

  isMenuOpen!: boolean;
  isSearchOpen!: boolean;

  faSearch = faMagnifyingGlass;
  faMenu = faBars;
  faClose = faXmark;

  constructor(
    private navStatus: NavStatusService,
    private searchbarStatus: SearchbarStatusService
  ) {}

  ngOnInit(): void {
    this.navbarSubs.add(
      this.navStatus.getState().subscribe((state) => (this.isMenuOpen = state))
    );

    this.navbarSubs.add(
      this.searchbarStatus
        .getState()
        .subscribe((state) => (this.isSearchOpen = state))
    );
  }

  ngOnDestroy(): void {
    this.navbarSubs.unsubscribe();
  }

  toggleMenu() {
    this.navStatus.toggleState();
  }

  toggleSearch() {
    this.searchbarStatus.toggleState();
  }
}
