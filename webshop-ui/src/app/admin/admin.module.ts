import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CaffpostsComponent } from './caffposts/caffposts.component';
import {RouterModule, Routes} from "@angular/router";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";

const routes: Routes = [
  {
    path: 'caffposts',
    component: CaffpostsComponent
  }
];

@NgModule({
  declarations: [CaffpostsComponent],
  imports: [
    CommonModule,
    MatCardModule,
    RouterModule.forChild(routes),
    MatButtonModule
  ]
})
export class AdminModule { }

