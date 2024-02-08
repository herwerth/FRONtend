import { RegistrationRoutingModule } from './registration-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ThankYouComponent } from './components/thank-you/thank-you.component';

@NgModule({
  declarations: [RegistrationFormComponent, RegistrationPageComponent, ThankYouComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    RegistrationRoutingModule
  ],
  exports: [RegistrationPageComponent],
})
export class RegistrationModule {}
