import { Injectable } from '@angular/core';
import { ClientesGym } from '../dto/clientesgym';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private endPointGetClients: string = 'http://localhost:8081/api/cliente/';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Headers': 'Content-Type',
  'Authorization': "Bearer "+localStorage.getItem('token')});

  constructor(private http: HttpClient) { }

  getListClients():Observable<ClientesGym[]>{
    return this.http.get<ClientesGym[]>(this.endPointGetClients, {headers: this.httpHeaders});
  }

}
