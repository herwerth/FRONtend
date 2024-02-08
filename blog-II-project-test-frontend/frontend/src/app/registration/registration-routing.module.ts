import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ThankYouComponent } from './components/thank-you/thank-you.component';
import { AuthGuard } from '../_guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: RegistrationPageComponent,
    canActivate: [AuthGuard],
    data: { mustNotBeLoggedIn: true },
  },
  {
    path: 'thank-you',
    component: ThankYouComponent,
    canActivate: [AuthGuard],
    data: { mustNotBeLoggedIn: true },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RegistrationRoutingModule {}
