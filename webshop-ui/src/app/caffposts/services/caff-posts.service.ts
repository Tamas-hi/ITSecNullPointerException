import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {CaffPost} from '../models/caff-post.model';
import {Comment} from '../models/comment.module';
import {saveAs} from 'file-saver';
import {AuthenticationService} from "../../authentication/services/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class CaffPostsService {

  public jsonData = [];
  public selectedCaffPost: Partial<CaffPost>;

  constructor(
    private http: HttpClient,
    private sanitizer: DomSanitizer,
    private authenticationService: AuthenticationService
  ) {
  }

  public convertImagesFromByteArray(data: string): SafeResourceUrl {
    return this.sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
  }

  public getById(caffPostId: number): Observable<CaffPost> {
    return this.http.get<CaffPost>('/api/caff-posts/' + caffPostId);
  }

  public getAll(): Observable<CaffPost[]> {
    return this.http.get<CaffPost[]>('/api/caff-posts/all');
  }

  public search(searchBy: string): Observable<CaffPost[]> {
    return this.http.get<CaffPost[]>('/api/caff-posts/search' + '?title=' + searchBy);
  }

  public getComments(caffPostId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>('/api/comments/' + caffPostId);
  }

  public addComment(caffPostId: number, userId: number, comment: string): Observable<void> {
    return this.http.post<void>('/api/comments/' + caffPostId + '/' + userId, comment);
  }

  public getCaffFile(caffPostId: number): Observable<ArrayBuffer> {
    return this.http.get(`/api/caff-files/${caffPostId}`, {responseType: 'arraybuffer'});
  }

  public download(file: ArrayBuffer, title: string): void {
    saveAs(new Blob([file], {type: 'application/octet-stream'}), `${title}.caff`);
  }

  public uploadCaff(base64: any): Observable<number> {
    return this.http.post<number>(`/api/caff-posts/upload/${this.authenticationService.loggedInUser.id}`, base64);
  }

  public uploadCaffDetails(caffPostId: number, title: string): Observable<void> {
    return this.http.post<void>(`/api/caff-posts/upload-details/${caffPostId}`, title);
  }

  public delete(caffPostId: number): Observable<void> {
    return this.http.delete<void>(`/api/caff-posts/${caffPostId}`);
  }
}
