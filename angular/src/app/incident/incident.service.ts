import { Injectable } from '@angular/core';
import { Incident } from '../dto/incident';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class IncidentService {

  private endPointIncidents: string = 'http://localhost:8081/api/incidents';
  private endPointUpdate: string = 'http://localhost:8081/api/incidents/update';
  private endPointIncidentsByDate: string = 'http://localhost:8081/api/incidents/filterByMonth';

  constructor(private http: HttpClient) { }

  addIncident(incident: Incident):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointIncidents, incident);
  }

  findAll():Observable<Incident[]>{
    return this.http.get<Incident[]>(this.endPointIncidents);
  }

  getIncidents(month: number, year: number):Observable<Incident[]>{
    var params = new HttpParams();
    params = params.append('month',''+month);
    params = params.append('year',''+ year);
    return this.http.get<Incident[]>(this.endPointIncidentsByDate, {params: params});
  }

  updateIncident(id: number):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointUpdate+'/'+id,null);
  }

}
