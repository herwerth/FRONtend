import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignInFormComponent } from './components/sign-in-form/sign-in-form.component';
import { SignInPageComponent } from './components/sign-in-page/sign-in-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { SignInRoutingModule } from './sign-in-routing.module';

@NgModule({
  declarations: [SignInFormComponent, SignInPageComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    ToastrModule.forRoot(),
    SignInRoutingModule,
  ],
  exports: [SignInPageComponent],
  providers: [ToastrService],
})
export class SignInModule {}
