import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { ProfileRoutingModule } from './profile-routing.module';
import { NavModule } from '../nav/nav.module';
import { UserDataFormComponent } from './components/user-data-form/user-data-form.component';
import { MyCommentsComponent } from './components/my-comments/my-comments.component';
import { LikedPostsComponent } from './components/liked-posts/liked-posts.component';
import { AccountSettingsComponent } from './components/account-settings/account-settings.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    ProfilePageComponent,
    UserDataFormComponent,
    MyCommentsComponent,
    LikedPostsComponent,
    AccountSettingsComponent,
  ],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    NavModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
  ],
  providers: [ToastrService],
})
export class ProfileModule {}
