import { NgModule, APP_INITIALIZER } from '@angular/core';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterModule } from '@angular/router';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthenticationService } from './sign-in/services/authentication.service';
import { AuthInterceptor } from 'src/interceptors/auth.interceptor';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppInitializerService } from './_services/app-initializer.service';
import { LocationStrategy, PathLocationStrategy } from '@angular/common';

export function initializeApp(appInitializer: AppInitializerService) {
  return () => appInitializer.initializeApp();
}

@NgModule({
  declarations: [AppComponent],
  imports: [
    RouterModule,
    AppRoutingModule,
    FontAwesomeModule,
    NgbModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    FontAwesomeModule,
    HttpClientModule,
  ],
  providers: [
    AuthenticationService,
    AppInitializerService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [AppInitializerService],
      multi: true,
    },
    { provide: LocationStrategy, useClass: PathLocationStrategy },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
