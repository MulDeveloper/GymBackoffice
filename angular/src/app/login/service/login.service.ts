import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { of, Observable } from 'rxjs';
import {  UserLogin } from '../../dto/userlogin';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private endPointLogin: string = 'http://localhost:8081/login';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Headers': 'Content-Type'});


  constructor(private http: HttpClient,private router: Router){ }

  log(user: UserLogin){
        this.http.post(this.endPointLogin, user).subscribe(
          (resp: any) => {
            localStorage.setItem('token', resp.token);
            localStorage.setItem('nomusu', resp.nomusu);
            location.replace('/index');
          },
          (error: HttpErrorResponse) => {
            swal(
              'Error',
              'Error en las credenciales',
              'error'
            )
          }

        );
  }

  private setSession(){
    //expiration

  }


  public isLogged(): boolean{
    if (localStorage.getItem('token') != null){
      return true;
      //TODO check valid jwt token
    }
    return false;
  }


  }
