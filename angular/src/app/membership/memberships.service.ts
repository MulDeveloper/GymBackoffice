import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Prices } from '../dto/prices';
import { of, Observable } from 'rxjs';
import { Membership } from '../dto/membership';

@Injectable({
  providedIn: 'root'
})
export class MembershipsService {

  private endPointPrices: string = 'http://localhost:8081/api/prices';
  private endPointMembership: string = 'http://localhost:8081/api/memberships';

  constructor(private http: HttpClient) { }

  getListPrices():Observable<Prices[]>{
    return this.http.get<Prices[]>(this.endPointPrices);
  }

  getOne(id: number):Observable<Membership>{
    var params = new HttpParams();
    params = params.append('id',''+id);
    return this.http.get<Membership>(this.endPointMembership, {params: params});
  }

  update(membership: Membership, months: number): Observable<Boolean>{
    var params = new HttpParams();
    params = params.append('months',''+months);
    return this.http.post<Boolean>(this.endPointMembership, membership, {params: params});
  }
}
