import { Component, OnInit } from '@angular/core';
import { PersonalService } from '../service/personal.service';
import { Personal } from '../../dto/personal';
import swal from 'sweetalert2';

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {

  public personal: Personal = new Personal();

  constructor(private service: PersonalService) { }

  ngOnInit(): void {


  }

  public create(){
    this.service.addPersonal(this.personal).subscribe(
    response => {
      //this.router.navigate(['/index'])
      swal("Correcto", `Staff ${response.nombrePersonal , response.apellidoPersonal} creado correctamente`, 'success')
    }
  )}
  

}
