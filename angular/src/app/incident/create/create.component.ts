import { Component, OnInit } from '@angular/core';
import { Incident } from '../../dto/incident';
import { IncidentService } from '../incident.service';
import swal from 'sweetalert2';


@Component({
  selector: 'app-create',
  templateUrl: './create.component.html'
})
export class CreateComponent implements OnInit {

  incident: Incident = new Incident();

  constructor(private service: IncidentService) { }

  ngOnInit(): void {
    this.incident.cost = 0.0;
  }

  public create(){
    var resp = this.service.addIncident(this.incident).subscribe();

    if (resp){
      swal("Correcto", `Incidencia creada correctamente`, 'success');
    }
    else{
      swal("Error", `Error al registrar al usuario`, 'error');
    }
  }

}
