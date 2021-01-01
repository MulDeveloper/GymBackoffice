import { Injectable } from '@angular/core';
import { Gymclass } from '../dto/gymclass';
import { Personal } from '../dto/personal';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClassesService {

  private endPointGetGymClasses: string = 'http://localhost:8081/api/classes';
  private endPointGetGymClassesStats: string = 'http://localhost:8081/api/classes/stats';
  private endPointGetGymClassesByWeek: string = 'http://localhost:8081/api/classes/selected';
  private endPointGetGymClassesCopy: string = 'http://localhost:8081/api/classes/copy';
  private endPointGetGymStaff: string = 'http://localhost:8081/api/getStaffWithRole';

  constructor(private http: HttpClient) { }


  addGymClass(gymclass: Gymclass):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointGetGymClasses, gymclass);
  }

  /*
  copyWeek(timeTable: Gymclass[]):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointGetGymClassesCopy, timeTable);
  }
  */

  copyWeek(from: string):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointGetGymClassesCopy+'/'+from,null);
  }

  delGymClass(id: string):Observable<Boolean>{
    var params = new HttpParams();
    params = params.append('id',id);
    return this.http.delete<Boolean>(this.endPointGetGymClasses, {params: params});
  }


  getListClasses(from: string, to: string):Observable<Gymclass[]>{
    return this.http.get<Gymclass[]>(this.endPointGetGymClasses);
  }

  getByWeek(from: string, to: string):Observable<Gymclass[]>{
    var params = new HttpParams();
    params = params.append('from',from);
    params = params.append('to',to);
    return this.http.get<Gymclass[]>(this.endPointGetGymClasses, {params: params});
  }

  getStaffByRoleMonitor():Observable<Personal[]>{
    var params = new HttpParams();
    params = params.append('role','MONITOR');
    return this.http.get<Personal[]>(this.endPointGetGymStaff, {params: params});
  }


  getStatsClass():Observable<Map<string,number>>{
    return this.http.get<Map<string,number>>(this.endPointGetGymClassesStats);
  }

}
