import { Component, OnInit } from '@angular/core';
import { PersonalService } from '../service/personal.service';
import { Personal } from '../../dto/personal';
import swal from 'sweetalert2';

@Component({
  selector: 'app-editpersonal',
  templateUrl: './editpersonal.component.html',
  styleUrls: ['./editpersonal.component.css']
})
export class EditpersonalComponent implements OnInit {

  personal : Personal;

  constructor(private service : PersonalService ) { }

  ngOnInit(): void {
    //we call the service and get the data of the current user
    this.service.getPersonal().subscribe(
      //asignamos
      personal => this.personal = personal
    )


  }

  edit(){
    swal({
        title: 'Quieres modificar tus datos??',
        text: "",
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, modificar'
      }).then((result) => {
        if (result.value) {
          //llamamos al servicio para hacer la peticion al Backend
          this.service.updatePersonal(this.personal).subscribe(
            response => {
              swal("Correcto", `Modificado correctamente`, 'success');
              location.replace('/editpersonal');
            },
            err => {
              swal("Error", `Error al modificar registro`, 'error');
            }
          );
        }
      })
  }

}
