import { Injectable } from '@angular/core';
import { Personal } from '../../dto/personal';
import { ModCredSec } from '../../dto/modcredsec';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonalService {

  private endPointAltaPersonal: string = 'http://localhost:8081/api/add';
  private endPointEditPersonal: string = 'http://localhost:8081/api/mod';
  private endPointUpdateCred: string = 'http://localhost:8081/api/modcred';
  private endPointListPersonal: string = 'http://localhost:8081/api/listapersonal';
  private endPointGetPersonal: string = 'http://localhost:8081/api/get';
  private endPointDelete: string = 'http://localhost:8081/api/del';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Headers': 'Content-Type',
  'Authorization': "Bearer "+localStorage.getItem('token')});


  constructor(private http: HttpClient) { }

  addPersonal(personal: Personal):Observable<Personal>{
    return this.http.post<Personal>(this.endPointAltaPersonal, personal, {headers: this.httpHeaders})

  }


  getListPersonal():Observable<Personal[]>{
    return this.http.get<Personal[]>(this.endPointListPersonal, {headers: this.httpHeaders});
  }

  deletePersonal(id:string): Observable<Boolean>{
    var params = new HttpParams();
    params = params.append('idpersonal',id);
    return this.http.delete<Boolean>(this.endPointDelete, {params: params, headers: this.httpHeaders});

  }

  updatePersonal(p: Personal):Observable<Personal>{
    return this.http.post<Personal>(this.endPointEditPersonal, p, {headers: this.httpHeaders})
  }

  getPersonal():Observable<Personal>{
    return this.http.get<Personal>(this.endPointGetPersonal, {headers: this.httpHeaders})
  }

  updateCred(cred: ModCredSec):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointUpdateCred, cred, {headers: this.httpHeaders})
  }


}
