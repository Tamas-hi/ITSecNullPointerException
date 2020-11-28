import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CaffFileSearcherComponent} from './components/caff-file-searcher/caff-file-searcher.component';
import {RouterModule, Routes} from '@angular/router';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {CaffFileUploadComponent} from './components/caff-file-upload/caff-file-upload.component';

const routes: Routes = [
  {
    path: 'search',
    component: CaffFileSearcherComponent
  }, {
    path: 'upload',
    component: CaffFileUploadComponent
  }
];

@NgModule({
  declarations: [CaffFileSearcherComponent, CaffFileUploadComponent],
  imports: [
    MatCardModule,
    CommonModule,
    RouterModule.forChild(routes),
    MatButtonModule
  ]
})
export class CaffPostsModule {
}
