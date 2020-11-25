import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CafffilesearcherComponent } from './components/cafffilesearcher/cafffilesearcher.component';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: 'search',
    component: CafffilesearcherComponent
  }
];

@NgModule({
  declarations: [CafffilesearcherComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class CaffpostsModule { }
