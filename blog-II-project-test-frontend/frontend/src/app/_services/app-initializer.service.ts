import { Injectable } from '@angular/core';
import { AuthenticationService } from '../sign-in/services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AppInitializerService {
  constructor(private authService: AuthenticationService) {}

  initializeApp() {
    return this.authService.getUser()
  }
}
