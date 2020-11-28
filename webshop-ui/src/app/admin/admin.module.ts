import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CaffPostsComponent} from './caff-posts/caff-posts.component';
import {RouterModule, Routes} from '@angular/router';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';

const routes: Routes = [
  {
    path: 'caff-posts',
    component: CaffPostsComponent
  }
];

@NgModule({
  declarations: [CaffPostsComponent],
  imports: [
    CommonModule,
    MatCardModule,
    RouterModule.forChild(routes),
    MatButtonModule
  ]
})
export class AdminModule {
}

