import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserDataFormComponent } from './components/user-data-form/user-data-form.component';
import { MyCommentsComponent } from './components/my-comments/my-comments.component';
import { LikedPostsComponent } from './components/liked-posts/liked-posts.component';
import { AccountSettingsComponent } from './components/account-settings/account-settings.component';
import { AuthGuard } from '../_guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: ProfilePageComponent,
    canActivate: [AuthGuard],
    data: { requiresLogin: true },
    children: [
      {
        path: '',
        redirectTo: 'user-info',
        pathMatch: 'full',
      },
      {
        path: 'user-info',
        component: UserDataFormComponent,
      },
      {
        path: 'my-comments',
        component: MyCommentsComponent,
      },
      {
        path: 'liked-posts',
        component: LikedPostsComponent,
      },
      {
        path: 'account-settings',
        component: AccountSettingsComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProfileRoutingModule {}
