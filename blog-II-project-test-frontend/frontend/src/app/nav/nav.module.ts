import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationMenuComponent } from './components/navigation-menu/navigation-menu.component';
import { TopBannerComponent } from './components/top-banner/top-banner.component';
import { MobileMenuComponent } from './components/mobile-menu/mobile-menu.component';
import { MobileHeaderComponent } from './components/mobile-header/mobile-header.component';
import { WebMenuComponent } from './components/web-menu/web-menu.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NavRoutingModule } from './nav-routing.module';
import { ContentModule } from '../content/content.module';

@NgModule({
  declarations: [
    TopBannerComponent,
    MobileMenuComponent,
    MobileHeaderComponent,
    WebMenuComponent,
    SearchBarComponent,
    NavigationMenuComponent,
  ],
  imports: [
    CommonModule,
    NavRoutingModule,
    FontAwesomeModule,
    ContentModule,
  ],
  exports: [NavigationMenuComponent, TopBannerComponent],
})
export class NavModule {}
