import { Component, OnInit } from '@angular/core';
import { ClientesGym } from '../../dto/clientesgym';
import { Prices } from '../../dto/prices';
import { ClienteService } from '../cliente.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-addclient',
  templateUrl: './addclient.component.html',
  styleUrls: ['./addclient.component.css']
})
export class AddclientComponent implements OnInit {

  public client: ClientesGym = new ClientesGym();
  tarifas: Prices[];
  idSelected: number;

  constructor(private service: ClienteService) { }

  ngOnInit(): void {
    this.getPrices();
  }

  getPrices(){
    this.service.getPrices().subscribe(
      tarifas => this.tarifas = tarifas
    );


  }

  public create(){
    //we send to the back the new client
    //we append a single param with the id of the prices that we want
    var resp = this.service.addClient(this.client, this.idSelected).subscribe();

    if(resp){
      swal("Correcto", `Cliente `+ this.client.nombreCliente + ' ' +  this.client.apellidoCliente + ` creado correctamente`, 'success');
    }
    else{
      swal("Error", `Error al registrar al usuario`, 'error');
    }

  }




}
