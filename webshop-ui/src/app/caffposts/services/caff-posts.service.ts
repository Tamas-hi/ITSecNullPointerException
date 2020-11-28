import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {User} from '../../authentication/models/user.model';
import {UserDetails} from '../../authentication/models/user-details.model';
import {tap} from 'rxjs/operators';
import {CaffPost} from '../models/caff-post.model';

@Injectable({
  providedIn: 'root'
})
export class CaffPostsService {


  public imagesFromByteArray = [];
  public jsonData = [];
  public byteArrayImages = [];
  public photo: SafeResourceUrl;

  constructor(
    private http: HttpClient,
    private sanitizer: DomSanitizer,
    private router: Router
  ) {
  }
  public getAll(): Observable<CaffPost[]> {
    return this.http.get<CaffPost[]>('/api/getAll');
   }

  public search(searchBy: string): Observable<CaffPost[]>{
    return this.http.get<CaffPost[]>('/api/search' + '?title=' + searchBy); // TODO más url
  }

}
