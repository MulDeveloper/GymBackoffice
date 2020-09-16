import { Injectable } from '@angular/core';
import { Personal } from '../../dto/personal';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonalService {

  private endPointAltaPersonal: string = 'http://localhost:8081/api/add';
  private endPointListPersonal: string = 'http://localhost:8081/api/listapersonal';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Headers': 'Content-Type'});


  constructor(private http: HttpClient) { }

  addPersonal(personal: Personal):Observable<Personal>{
    return this.http.post<Personal>(this.endPointAltaPersonal, personal, {headers: this.httpHeaders});
  }

  getListPersonal():Observable<Personal[]>{
    var params = new HttpParams();
    params = params.append('nomusu',localStorage.getItem('nomusu'));
    params = params.append('token',localStorage.getItem('auth_token'));
    return this.http.get<Personal[]>(this.endPointListPersonal, {params: params, headers: this.httpHeaders});
  }


}
