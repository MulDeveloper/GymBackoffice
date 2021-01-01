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



  constructor(private http: HttpClient) { }

  addPersonal(personal: Personal):Observable<Personal>{
    return this.http.post<Personal>(this.endPointAltaPersonal, personal)

  }


  getListPersonal():Observable<Personal[]>{
    return this.http.get<Personal[]>(this.endPointListPersonal);
  }

  deletePersonal(id:string): Observable<Boolean>{
    var params = new HttpParams();
    params = params.append('idpersonal',id);
    return this.http.delete<Boolean>(this.endPointDelete, {params: params});

  }

  updatePersonal(p: Personal):Observable<Personal>{
    return this.http.post<Personal>(this.endPointEditPersonal, p)
  }

  getPersonal():Observable<Personal>{
    return this.http.get<Personal>(this.endPointGetPersonal)
  }

  updateCred(cred: ModCredSec):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointUpdateCred, cred)
  }


}
