import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NavStatusService {
  private isMobileMenuVisibleSubject = new Subject<boolean>();
  isMobileMenuVisible: boolean = false;
  constructor() {}

  toggleState(): void {
    this.isMobileMenuVisible = !this.isMobileMenuVisible;
    this.isMobileMenuVisibleSubject.next(this.isMobileMenuVisible);
  }

  getState(): Observable<boolean> {
    return this.isMobileMenuVisibleSubject.asObservable();
  }
}
