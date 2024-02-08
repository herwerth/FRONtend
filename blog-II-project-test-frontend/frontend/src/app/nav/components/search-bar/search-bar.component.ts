import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import {
  Component,
  DoCheck,
  ElementRef,
  Input,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { faMagnifyingGlass, faXmark } from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { SearchbarStatusService } from 'src/app/nav/services/searchbar-status.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
  animations: [
    trigger('toggleSearch', [
      state(
        'open',
        style({
          opacity: '1',
          zIndex: '100',
        })
      ),
      state(
        'closed',
        style({
          opacity: '0',
          zIndex: '-1',
        })
      ),
      transition('closed => open', [animate('0.1s')]),
      transition('open => closed', [animate('0.05s')]),
    ]),
  ],
})
export class SearchBarComponent implements OnInit, OnDestroy, DoCheck {
  @Input() isSearchOpen!: boolean;
  @ViewChild('searchInputField', { static: false })
  searchInputField!: ElementRef<HTMLInputElement>;

  ngDoCheck(): void {
    if (this.isSearchOpen && this.searchInputField) {
      this.searchInputField.nativeElement.focus();
    }
  }

  searchSubscription: Subscription = new Subscription();

  faSearch = faMagnifyingGlass;
  faClose = faXmark;

  constructor(private searchbarStatus: SearchbarStatusService) {}

  ngOnInit(): void {
    this.searchSubscription.add(
      this.searchbarStatus
        .getState()
        .subscribe((state) => (this.isSearchOpen = state))
    );
  }

  ngOnDestroy(): void {
    this.searchSubscription.unsubscribe();
  }

  toggleSearch() {
    this.searchbarStatus.toggleState();
  }
}
