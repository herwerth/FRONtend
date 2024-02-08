import { Component } from '@angular/core';
import {
  faFacebookF,
  faXTwitter,
  faYoutube,
} from '@fortawesome/free-brands-svg-icons';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { SearchbarStatusService } from 'src/app/nav/services/searchbar-status.service';

@Component({
  selector: 'app-web-menu',
  templateUrl: './web-menu.component.html',
  styleUrls: ['./web-menu.component.css'],
})
export class WebMenuComponent {
  faFacebookF = faFacebookF;
  faXTwitter = faXTwitter;
  faYoutube = faYoutube;
  faSearch = faMagnifyingGlass;

  searchSubscription: Subscription = new Subscription();

  isSearchOpen!: boolean;
  
  constructor(private searchbarStatus: SearchbarStatusService) {}

  ngOnInit(): void {
    this.searchSubscription.add(
      this.searchbarStatus
        .getState()
        .subscribe((state) => (this.isSearchOpen = state))
    );
  }

  toggleSearch() {
    this.searchbarStatus.toggleState();
  }
}
