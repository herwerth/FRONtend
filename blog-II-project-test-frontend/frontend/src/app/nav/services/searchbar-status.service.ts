import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SearchbarStatusService {
  private isSearchBarVisibleSubject = new Subject<boolean>();
  isSearchBarVisible: boolean = false;
  constructor() {}

  toggleState(): void {
    this.isSearchBarVisible = !this.isSearchBarVisible;
    this.isSearchBarVisibleSubject.next(this.isSearchBarVisible);
  }

  getState(): Observable<boolean> {
    return this.isSearchBarVisibleSubject.asObservable();
  }
}
