import { Component, OnInit } from '@angular/core';
import { PersonalService } from '../../service/personal.service';
import { ModCredSec } from '../../../dto/modcredsec';
import swal from 'sweetalert2';

@Component({
  selector: 'app-editpass',
  templateUrl: './editpass.component.html',
  styleUrls: ['./editpass.component.css']
})
export class EditpassComponent implements OnInit {

  passActual: string;
  passNueva: string;

  constructor(private service: PersonalService) { }

  ngOnInit(): void {
  }

  mod(){
    var cred = new ModCredSec();
    cred.actual = btoa(this.passActual);
    cred.nueva = btoa(this.passNueva);
    cred.nomusu = localStorage.getItem('nomusu');
    cred.token = localStorage.getItem('auth_token');
    swal({
        title: 'Quieres modificar tus credenciales??',
        text: "",
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, modificar'
      }).then((result) => {
        if (result.value) {
          //llamamos al servicio para hacer la peticion al Backend
          this.service.updateCred(cred).subscribe(
            response => {
              if(response.valueOf()){
                swal("Correcto", `Modificado correctamente`, 'success');
                location.replace('/index');
              }
              else{
                swal("Error", `Error al modificar registro`, 'error');
              }
            },
            err => {
              swal("Error", `Error al modificar registro`, 'error');
            }
          );
        }
      })

  }

}
