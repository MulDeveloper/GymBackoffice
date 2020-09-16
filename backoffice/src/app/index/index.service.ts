import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { of, Observable } from 'rxjs';
import { ClienteMatricula } from '../dto/clientematricula';

@Injectable({
  providedIn: 'root'
})
export class IndexService {

  private endPointIndex: string = 'http://localhost:8081/api/index';
  private endPointLastUsers: string = 'http://localhost:8081/api/index/clientesLast';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Headers': 'Content-Type'});

  constructor(private http: HttpClient,private router: Router) { }

  getData(): Observable<Map<string, object>>{
    var params = new HttpParams();
    params = params.append('token',localStorage.getItem('auth_token'));
    params = params.append('nomusu',localStorage.getItem('nomusu'));
    return this.http.get<Map<string,object>>(this.endPointIndex, {params:params});
  }

  getClients(): Observable<Map<number, ClienteMatricula>>{
    var params = new HttpParams();
    params = params.append('token',localStorage.getItem('auth_token'));
    params = params.append('nomusu',localStorage.getItem('nomusu'));
    return this.http.get<Map<number,ClienteMatricula>>(this.endPointLastUsers, {params:params});
  }



}
