import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../authentication/services/authentication.service';
import {User} from '../../../authentication/models/user.model';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public get loggedInUser(): User {
    return this.authenticationService.loggedInUser;
  }

  constructor(
    private authenticationService: AuthenticationService,
    private httpClient: HttpClient
  ) {
  }

  public ngOnInit(): void {
  }

  doProblem() {
    this.httpClient.delete('/api/caff-pos/1').subscribe(ans => {
      console.log('doproblem', ans);
    });
  }
}
