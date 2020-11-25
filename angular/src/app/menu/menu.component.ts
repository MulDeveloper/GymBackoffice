import { Component, OnInit } from '@angular/core';
import { MenuService } from './menu.service';
import { ClientesGym } from '../dto/clientesgym';
import { Personal } from '../dto/personal';
import { PersonalService } from '../personal/service/personal.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  listaTemporal:  ClientesGym[];
  personal: Personal;
  no_results_found: Boolean;

  constructor(private service: MenuService, private servicePersonal: PersonalService) { }

  ngOnInit(): void {
    //obtenemos los datos del personal para popular el menu de cuenta
    this.getDataPersonal();
  }

  getDataPersonal(){
    this.servicePersonal.getPersonal().subscribe(
        personal => this.personal = personal
    );

  }


  public search(value:string){

    if(value === ''){
      this.listaTemporal = null;
    }
    else{
      this.service.getListFindByInput(value).subscribe(
        //asignamos
        listaTemporal => this.listaTemporal = listaTemporal,
        error => {console.log('no hay registros')}
      );
    }
  }

}
