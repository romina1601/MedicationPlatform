import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';

import { ToastrService } from 'ngx-toastr';

import { AuthService } from '../../shared/services/auth.service';
import { StorageService } from '../../shared/services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  // will grab the error from a failed request
  error: string = '';
  // helper flag, it will be set to true while processing a request
  loading: boolean = false;
  subscription: Subscription;
  // will hold the username of the user that has logged in
  public username: string;

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    /**
     * Since the http.incerceptor will redirect the user to the login page
     * passing some queryParams ( { unauthorized: true }, http.incerceptor.ts | line: 91 ),
     * a subscription to the ActivatedRoute.queryParams is needed in order to fetch (read) and check the parameter value
     *
     * The subscribe method will return an Observavble (kind of a Promise), more details here:
     * https://angular-2-training-book.rangle.io/handout/routing/query_params.html
     */
    this.subscription = this.route.queryParams.subscribe((params: Params) => {
      /** If unauthorized flag has been set to true
       * a bootstrap Alert message will be shown above the login form */
      if (params['unauthorized']) {
        this.error = 'Access denied';
      }
      /** If user has already logged in
       * redirect to the overview match page */
      if (this.authService.isLoggedIn() && !this.error) {
        this.router.navigate(['/']);
      }
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onLogin(form: NgForm) {
    /** Save each form value into a variable */
    const { username, password } = form.value;
    const authorizationCode: string = btoa(`${username}:${password}`);

    /** Check if the form is valid */
    if (form.valid) {
      /** reset error if has been set before (will hide the bootstrap Alert message) */
      this.error = '';
      this.loading = true;

      /** Save the app_token, role_token and username in the local storage
       *  and route the users according to their role
       */
      this.authService.login(username, password, authorizationCode).subscribe(
        (response: any) => {
            this.storageService.set(this.storageService.app_token, authorizationCode);
            this.storageService.set(this.storageService.role_token, response.authorities[0].authority);
            if(this.storageService.get(this.storageService.role_token) === 'ROLE_DOCTOR') {
              this.router.navigate(['/doctor']);
            }
            if(this.storageService.get(this.storageService.role_token) === 'ROLE_PATIENT') {
              this.username  = username;
              this.storageService.set("username", this.username);
              this.router.navigate(['/patient']);
            }
            if(this.storageService.get(this.storageService.role_token) === 'ROLE_CAREGIVER') {
              this.username  = username;
              this.storageService.set("username", this.username);
              this.router.navigate(['/caregiver']);
            }
            //this.router.navigate(['/']);
        
      },
        /** If we got any error, display a bootstrap alert */
        (error: HttpErrorResponse) => {
            this.error =
                error.status === 401 || !error.status ? 'Username or password incorrect' : 'Oh snap! Something got wrong';
            this.toastr.error(this.error);
            /** Reset loading to false
             * (will stop the loading animation on the login button) */
            this.loading = false;
        }
    );
    }
  }
}