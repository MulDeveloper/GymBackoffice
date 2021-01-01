import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../cliente.service';
import { ClientesGym } from '../../dto/clientesgym';
import { Membership } from '../../dto/membership';
import { Prices } from '../../dto/prices';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import swal from 'sweetalert2';

@Component({
  selector: 'app-listclient',
  templateUrl: './listclient.component.html',
  styleUrls: ['./listclient.component.css']
})
export class ListclientComponent implements OnInit {

  listClients: ClientesGym[];
  editProfileGroup: FormGroup;
  membership: Membership;

  constructor(
    private service: ClienteService,
    private fb: FormBuilder,
    private modalService: NgbModal) {

      //form modal
      this.editProfileGroup = this.fb.group({
       idcliente: [''],
       idMatricula: [''],
       nombreCliente: [''],
       apellidoCliente: [''],
       nifCliente: [''],
       emailCliente: [''],
       telCliente: [''],
       direccionCliente: [''],
       fechaNacimiento: ['']
      });


     }

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
          this.deleteService(id);
        }
      })

  }

  private deleteService(id: number){
    this.service.delClient(id).subscribe(
      response => {
        if(response){
          location.replace('/listclients');
        }
        else{
          swal("Error", `Error al eliminar registro`, 'error');
        }
      },
      err => {
        swal("Error", `Error al eliminar registro`, 'error');
      }
    )
  }

  public showModalEdit(modal, cliente: ClientesGym){
      this.modalService.open(modal, {
       centered: true,
       backdrop: 'static'
      });


      this.editProfileGroup.patchValue({
        idcliente: cliente.idcliente,
        idMatricula: cliente.idMatricula,
        nombreCliente: cliente.nombreCliente,
        apellidoCliente: cliente.apellidoCliente,
        nifCliente: cliente.nifCliente,
        emailCliente: cliente.emailCliente,
        telCliente: cliente.telCliente,
        direccionCliente: cliente.direccionCliente,
        fechaNacimiento: cliente.fechaNacimiento
      });
    }

    onSubmit() {
      var newClient = new ClientesGym(this.editProfileGroup.getRawValue());

      //llamamos al servicio y actualizamos el usuario
      swal({
          title: 'Quieres modificar a '+ newClient.nombreCliente + ' ' + newClient.apellidoCliente +'?',
          text: "",
          type: 'question',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Si, modificar'
        }).then((result) => {
          if (result.value) {
            //llamamos al servicio para hacer la peticion al Backend
            this.service.updateClient(newClient).subscribe(
              response => {
                swal("Correcto", `Modificado correctamente`, 'success');
                location.replace('/listclients');
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
