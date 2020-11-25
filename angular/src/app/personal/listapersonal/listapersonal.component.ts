import { Component, OnInit } from '@angular/core';
import { Personal } from '../../dto/personal';
import { PersonalService } from '../service/personal.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listapersonal',
  templateUrl: './listapersonal.component.html',
  styleUrls: ['./listapersonal.component.css']
})
export class ListapersonalComponent implements OnInit {

  listaPersonal:  Personal[];
  showProfileGroup: FormGroup;
  editProfileGroup: FormGroup;

  personalAux: Personal;

  constructor(private service: PersonalService,
  private fb: FormBuilder,
  private modalService: NgbModal,
  private router : Router) {

    //form modal
    this.showProfileGroup = this.fb.group({
     nombrePersonal: [''],
     nifPersonal: [''],
     direccion: [''],
     rol: [''],
     email: [''],
     sueldo: [''],
     fechaNac: ['']
    });

    //edit modal
    //form modal
    this.editProfileGroup = this.fb.group({
     idpersonal: [''],
     nombrePersonal: [''],
     apellidoPersonal: [''],
     nifPersonal: [''],
     direccionPersonal: [''],
     rol: [''],
     emailPersonal: [''],
     sueldoBruto: [''],
     fechaAlta: [''],
     fechaNacimiento: ['']
    });

   }

  ngOnInit(): void {
    this.service.getListPersonal().subscribe(
      //asignamos
      listaPersonal => this.listaPersonal = listaPersonal
    );

    console.log('tam', this.listaPersonal.length);

  }

  public showModal(modal, personal: Personal){
    //obtenemos un registro en concreto con el id de personal (hidden en el html)
    this.modalService.open(modal, {
     centered: true,
     backdrop: 'static'
    });

    this.showProfileGroup.patchValue({
     nombrePersonal: personal.nombrePersonal + ' ' + personal.apellidoPersonal,
     nifPersonal: personal.nifPersonal,
     direccion: personal.direccionPersonal,
     rol: personal.rol,
     email: personal.emailPersonal,
     sueldo: personal.sueldoBruto,
     fechaNac: personal.fechaNacimiento,
    });
  }

  public delete(id: number, nom: string){
    swal({
        title: 'Quieres eliminar a '+ nom +'?',
        text: "",
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, eliminar'
      }).then((result) => {
        if (result.value) {
          //llamamos al servicio para hacer la peticion POST al Backend
          this.deleteService(id);
        }
      })

  }

  private deleteService(id: number){
    //llamamos al service para hacer un delete por idea
    this.service.deletePersonal(''+id).subscribe(
      response => {
        swal("Correcto", `Eliminado correctamente`, 'success');
        location.replace('/listapersonal');
      },
      err => {
        swal("Error", `Error al eliminar registro`, 'error');
      }
    )
  }

  public CalculateAge(fecha: Date): number {
      var timeDiff = Math.abs(Date.now() - new Date(fecha).getTime());
      return Math.floor(timeDiff / (1000 * 3600 * 24) / 365.25);

  }


  public showModalEdit(modal, personal: Personal){
      this.modalService.open(modal, {
       centered: true,
       backdrop: 'static'
      });


      this.editProfileGroup.patchValue({
       idpersonal: personal.idpersonal,
       nombrePersonal: personal.nombrePersonal,
       apellidoPersonal: personal.apellidoPersonal,
       nifPersonal: personal.nifPersonal,
       direccionPersonal: personal.direccionPersonal,
       rol: personal.rol,
       emailPersonal: personal.emailPersonal,
       sueldoBruto: personal.sueldoBruto,
       fechaAlta: personal.fechaAlta,
       fechaNacimiento: personal.fechaNacimiento
      });
    }

    onSubmit() {
      var personalNuevo = new Personal(this.editProfileGroup.getRawValue());

      //llamamos al servicio y actualizamos el usuario
      swal({
          title: 'Quieres modificar a '+ personalNuevo.nombrePersonal +'?',
          text: "",
          type: 'question',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Si, modificar'
        }).then((result) => {
          if (result.value) {
            //llamamos al servicio para hacer la peticion al Backend
            this.service.updatePersonal(personalNuevo).subscribe(
              response => {
                swal("Correcto", `Modificado correctamente`, 'success');
                location.replace('/listapersonal');
              },
              err => {
                swal("Error", `Error al modificar registro`, 'error');
              }

            );
            this.modalService.dismissAll();
          }
          else{
            this.modalService.dismissAll();
          }
        })
    }


}
