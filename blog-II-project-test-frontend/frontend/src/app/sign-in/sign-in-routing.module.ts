import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInPageComponent } from './components/sign-in-page/sign-in-page.component';
import { AuthGuard } from '../_guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: SignInPageComponent,
    canActivate: [AuthGuard],
    data: { mustNotBeLoggedIn: true },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SignInRoutingModule {}
