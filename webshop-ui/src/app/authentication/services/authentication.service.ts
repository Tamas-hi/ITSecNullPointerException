import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/authentication/models/user.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import * as moment from 'moment';
import { Moment } from 'moment';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient) {
    }

    public register(userName: string, password: string): Observable<any> {
        return this.http.post<User>('/api/register', { userName, password })
            .pipe(tap(authResult => this.setSession(authResult)));
    }

    public login(userName: string, password: string): Observable<any> {
        return this.http.post<User>('/api/login', { userName, password })
            .pipe(tap(authResult => this.setSession(authResult)));
    }

    private setSession(authResult: { idToken: string; expiresIn: string; }): void {
        const expiresAt = moment().add(authResult.expiresIn, 'second');

        localStorage.setItem('id_token', authResult.idToken);
        localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()));
    }

    public logout(): void {
        localStorage.removeItem('id_token');
        localStorage.removeItem('expires_at');
    }

    public isLoggedIn(): boolean {
        return moment().isBefore(this.getExpiration());
    }

    public isLoggedOut(): boolean {
        return !this.isLoggedIn();
    }

    public getExpiration(): Moment {
        const expiration = localStorage.getItem('expires_at');
        const expiresAt = JSON.parse(expiration);
        return moment(expiresAt);
    }
}
