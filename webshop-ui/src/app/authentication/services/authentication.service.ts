import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from 'src/app/authentication/models/user.model';
import {Observable, Subject} from 'rxjs';
import {tap} from 'rxjs/operators';
import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot} from '@angular/router';
import {KEY_LOGGED_IN_USER_ID, MESSAGE_ACCESS_DENIED, MESSAGE_LOG_IN_NEEDED, URL_ACCESS} from '../constants';
import {UserDetails} from '../models/user-details.model';
import {SnackBarHelperUtil} from '../../core/utils/snack-bar-helper.util';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Role} from "../enums/role.enum";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService implements CanLoad, CanActivate {

  public loggedInUser: User;

  constructor(
    private http: HttpClient,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {
  }

  public register(user: User): Observable<boolean> {
    return this.http.post<boolean>('/api/register', user);
  }

  public login(userLoginInfo: User): Observable<UserDetails> {
    return this.http.post<UserDetails>('/api/login', userLoginInfo).pipe(tap((userDetails) => {
      this.setLoggedInUserId(userDetails.userId);
    }));
  }

  public logout(): Observable<boolean> {
    return this.http.get<boolean>('/api/logout').pipe(tap(() => {
      this.removeLoggedInUser();
      this.loggedInUser = undefined;
    }));
  }

  public getLoggedInUser(id: number): Observable<User> {
    return this.http.get<User>(`/api/user/${id}`);
  }

  private get loggedInUserId(): number {
    return +localStorage.getItem(KEY_LOGGED_IN_USER_ID);
  }

  private setLoggedInUserId(id: number): void {
    localStorage.setItem(KEY_LOGGED_IN_USER_ID, id.toString());
  }

  private removeLoggedInUser(): void {
    localStorage.removeItem(KEY_LOGGED_IN_USER_ID);
  }

  public canLoad(route: Route): Observable<boolean> | boolean {
    return this.hasUrlAccess(route.path);
  }

  public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.hasUrlAccess(state.url);
  }

  private hasUrlAccess(path: string): Observable<boolean> | boolean {
    if (!this.loggedInUser) {
      const subject = new Subject<boolean>();
      if (this.loggedInUserId) {
        this.getLoggedInUser(this.loggedInUserId).subscribe(user => {
          this.loggedInUser = user;
          this.check(path, subject);
        });
      } else {
        this.logout().subscribe(() => {
          subject.next(false);
          this.router.navigate(['/auth/login']).then(() =>
            SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_LOG_IN_NEEDED, true));
        });
      }

      return subject;
    } else {
      return this.check(path);
    }
  }

  private check(path: string, subject?: Subject<boolean>): boolean | null {
    const neededRoles = URL_ACCESS.get(path);
    let hasAccess = false;

    if (neededRoles) {
      URL_ACCESS.get(path).forEach(neededRole => {
        if (this.loggedInUser.roles.map(item => item.role).find(userRole => userRole === neededRole)) {
          hasAccess = true;
        }
      });
    } else {
      hasAccess = true;
    }

    if (!hasAccess) {
      this.router.navigate(['/auth/login']).then(() =>
        SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_ACCESS_DENIED, true));
    }

    if (subject) {
      subject.next(hasAccess);
      return null;
    } else {
      return hasAccess;
    }
  }

  public get isUser(): boolean {
    return !!this.loggedInUser.roles.map(item => item.role).find(role => role === Role.USER);
  }

  public get isAdmin(): boolean {
    return !!this.loggedInUser.roles.map(item => item.role).find(role => role === Role.ADMIN);
  }
}
