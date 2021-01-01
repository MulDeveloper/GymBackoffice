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
  private endPointLastUsers: string = 'http://localhost:8081/api/index/clientsLast';
  private endPointCosts: string = 'http://localhost:8081/api/incidents/costs';


  constructor(private http: HttpClient,private router: Router) { }

  getData(): Observable<Map<string, object>>{
    return this.http.get<Map<string,object>>(this.endPointIndex);
  }

  getClients(): Observable<Map<number, ClienteMatricula>>{
    return this.http.get<Map<number,ClienteMatricula>>(this.endPointLastUsers);
  }

  getCost(): Observable<number>{
    return this.http.get<number>(this.endPointCosts);
  }



}
