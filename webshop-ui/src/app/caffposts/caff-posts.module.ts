import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CaffFileSearcherComponent} from './components/caff-file-searcher/caff-file-searcher.component';
import {RouterModule, Routes} from '@angular/router';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {CaffFileUploadComponent} from './components/caff-file-upload/caff-file-upload.component';
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {FlexModule} from "@angular/flex-layout";

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
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    FlexModule
  ]
})
export class CaffPostsModule {
}
