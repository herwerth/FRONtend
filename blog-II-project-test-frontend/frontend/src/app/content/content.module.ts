import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FebruaryComponent } from './components/february/february.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { ContentRoutingModule } from './content-routing.module';

@NgModule({
  declarations: [FebruaryComponent, PagenotfoundComponent],
  imports: [CommonModule, ContentRoutingModule],
  exports: [],
})
export class ContentModule {}
