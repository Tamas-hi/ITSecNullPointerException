import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from 'src/app/authentication/models/user.model';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {LOGGED_IN_USER_KEY} from '../constants/string-resources.constant';
import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, RouterStateSnapshot} from '@angular/router';
import {URL_ACCESS} from "../constants/url-roles.constant";
import {Role} from "../enums/role.enum";
import {UserDetails} from "../models/user-details.model";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService implements CanLoad, CanActivate {

  private roles: Role[] = [];

  constructor(private http: HttpClient) {
  }

  public register(user: User): Observable<boolean> {
    return this.http.post<boolean>('/api/register', user);
  }

  public login(user: User): Observable<UserDetails> {
    return this.http.post<UserDetails>('/api/login', user).pipe(tap((userDetails) => {
      user.roles = [];
      userDetails.authorities.forEach(detail => {
        user.roles.push(detail.authority as Role);
      });

      this.roles = user.roles;
      this.setLoggedInUser(user);
    }));
  }

  public logout(): Observable<boolean> {
    return this.http.get<boolean>('/api/logout').pipe(tap(() => this.removeLoggedInUser()));
  }

  public getLoggedInUser(): User {
    return JSON.parse(localStorage.getItem(LOGGED_IN_USER_KEY)) as User;
  }

  private setLoggedInUser(user: User): void {
    localStorage.setItem(LOGGED_IN_USER_KEY, JSON.stringify(user));
  }

  private removeLoggedInUser(): void {
    localStorage.removeItem(LOGGED_IN_USER_KEY);
  }

  public canLoad(route: Route): Observable<boolean> | boolean {
    return this.hasUrlAccess(route.path);
  }

  public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.hasUrlAccess(state.url);
  }

  private hasUrlAccess(path: string): boolean {
    let hasAccess = false;

    URL_ACCESS.get(path).forEach(neededRole => {
      if (this.roles.find(userRole => userRole === neededRole)) {
        hasAccess = true;
      }
    });

    return hasAccess;
  }
}
