import { Component, OnInit } from '@angular/core';
import { Personal } from '../../dto/personal';
import { PersonalService } from '../service/personal.service';

@Component({
  selector: 'app-listapersonal',
  templateUrl: './listapersonal.component.html',
  styleUrls: ['./listapersonal.component.css']
})
export class ListapersonalComponent implements OnInit {

  listaPersonal:  Personal[];

  constructor(private service: PersonalService) { }

  ngOnInit(): void {
    this.service.getListPersonal().subscribe(
      //asignamos
      listaPersonal => this.listaPersonal = listaPersonal
    );
  }

  public show(id: number){
    //obtenemos un registro en concreto con el id de personal (hidden en el html)
    console.log(id);
  }

  public delete(id: number){
    //obtenemos un registro en concreto con el id de personal (hidden en el html)
    console.log(id);
  }

  public edit(id: number){
    //obtenemos un registro en concreto con el id de personal (hidden en el html)
    console.log(id);
  }

  public CalculateAge(fecha: Date): number {
      var timeDiff = Math.abs(Date.now() - new Date(fecha).getTime());
      return Math.floor(timeDiff / (1000 * 3600 * 24) / 365.25);

      }

}
