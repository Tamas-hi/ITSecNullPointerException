import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CafffilesearcherComponent } from './components/cafffilesearcher/cafffilesearcher.component';
import {RouterModule, Routes} from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import {MatButtonModule} from "@angular/material/button";

const routes: Routes = [
  {
    path: 'search',
    component: CafffilesearcherComponent
  }
];

@NgModule({
  declarations: [CafffilesearcherComponent],
  imports: [
    MatCardModule,
    CommonModule,
    RouterModule.forChild(routes),
    MatButtonModule
  ]
})
export class CaffpostsModule { }
