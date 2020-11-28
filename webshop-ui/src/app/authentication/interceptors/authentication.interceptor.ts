import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackBarHelperUtil} from '../../core/utils/snack-bar-helper.util';
import {MESSAGE_NOT_ALLOWED, MESSAGE_LOG_IN_NEEDED, MESSAGE_UNSUCCESSFUL_LOGIN} from '../constants';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(
    private matSnackBar: MatSnackBar,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 403) {
          SnackBarHelperUtil.showMessage(this.matSnackBar, MESSAGE_NOT_ALLOWED, true);
        }
        return throwError(error);
      })
    );
  }
}
