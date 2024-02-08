import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { NavigationMenuComponent } from './components/navigation-menu/navigation-menu.component';
import { FebruaryComponent } from '../content/components/february/february.component';

const routes: Routes = [
  {
    path: '',
    component: NavigationMenuComponent,
    children: [{ path: '', component: FebruaryComponent }],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NavRoutingModule {}
