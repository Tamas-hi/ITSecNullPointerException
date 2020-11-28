import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from 'src/app/core/app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Router, RouterModule, Routes} from '@angular/router';
import {AuthenticationModule} from 'src/app/authentication/authentication.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import {HomeComponent} from './components/home/home.component';
import {AuthenticationService} from '../authentication/services/authentication.service';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {AuthenticationInterceptor} from '../authentication/interceptors/authentication.interceptor';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthenticationService],
    canLoad: [AuthenticationService]
  },
  {
    path: 'auth',
    loadChildren: () => import('../authentication/authentication.module').then(m => m.AuthenticationModule)
  },
  {
    path: 'caff-posts',
    loadChildren: () => import('../caffposts/caff-posts.module').then(m => m.CaffPostsModule)
  },
  {
    path: 'admin',
    loadChildren: () => import('../admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
  },
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    AuthenticationModule,
    FlexLayoutModule,
    MatButtonModule,
    MatCardModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true,
      deps: [MatSnackBar, AuthenticationService, Router]
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
