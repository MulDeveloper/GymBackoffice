import { Injectable } from '@angular/core';
import { ClientesGym } from '../dto/clientesgym';
import { Observable } from 'rxjs';
import { PersonalService } from '../personal/service/personal.service';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private endPointSearch: string = 'http://localhost:8081/api/cliente/search';


  constructor(private http: HttpClient, private service: PersonalService) { }

  getListFindByInput(value:string):Observable<ClientesGym[]>{
    try{
      return this.http.get<ClientesGym[]>(this.endPointSearch+'/'+value);
    }
    catch(HttpErrorResponse){
      console.log('error');
    }
  }
}
