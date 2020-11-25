import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../cliente.service';
import { ClientesGym } from '../../dto/clientesgym';

@Component({
  selector: 'app-listclient',
  templateUrl: './listclient.component.html',
  styleUrls: ['./listclient.component.css']
})
export class ListclientComponent implements OnInit {

  listClients: ClientesGym[];

  constructor(private service: ClienteService) { }

  ngOnInit(): void {
    this.getClientsList();
  }

  getClientsList(){
    this.service.getListClients().subscribe(
      listClients => this.listClients = listClients
    );

    console.log(this.listClients.length);

  }

  public CalculateAge(fecha: Date): number {
      var timeDiff = Math.abs(Date.now() - new Date(fecha).getTime());
      return Math.floor(timeDiff / (1000 * 3600 * 24) / 365.25);

  }








}
