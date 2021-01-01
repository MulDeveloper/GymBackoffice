import { Injectable } from '@angular/core';
import { ClientesGym } from '../dto/clientesgym';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Prices } from '../dto/prices';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private endPointClients: string = 'http://localhost:8081/api/cliente';
  private endPointClientGetOne: string = 'http://localhost:8081/api/cliente/byId';
  private endPointClientUpdate: string = 'http://localhost:8081/api/cliente/update';
  private endPointGetPrices: string = 'http://localhost:8081/api/prices/';
  private endPointGetStats: string = 'http://localhost:8081/api/cliente/stats';

  constructor(private http: HttpClient) { }

  getListClients():Observable<ClientesGym[]>{
    return this.http.get<ClientesGym[]>(this.endPointClients);
  }

  getPrices():Observable<Prices[]>{
    return this.http.get<Prices[]>(this.endPointGetPrices);
  }


  addClient(client: ClientesGym, id: number):Observable<Boolean>{
    var params = new HttpParams();
    params = params.append('idprice',''+id);
    return this.http.post<Boolean>(this.endPointClients, client, {params: params});
  }

  delClient(id: number):Observable<Boolean>{
    var params = new HttpParams();
    params = params.append('id',''+id);
    return this.http.delete<Boolean>(this.endPointClients, {params: params});
  }

  updateClient(client: ClientesGym):Observable<Boolean>{
    return this.http.post<Boolean>(this.endPointClientUpdate, client,);
  }

  getOneClient(id: string):Observable<ClientesGym>{
    var params = new HttpParams();
    params = params.append('id',''+id);
    return this.http.get<ClientesGym>(this.endPointClientGetOne, {params: params});
  }

  getStatsByAge():Observable<Map<string, number>>{
    return this.http.get<Map<string, number>>(this.endPointGetStats);
  }


}
