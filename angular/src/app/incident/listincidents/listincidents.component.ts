import { Component, OnInit } from '@angular/core';
import { Incident } from '../../dto/incident';
import {IncidentService } from '../incident.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-listincidents',
  templateUrl: './listincidents.component.html'
})
export class ListincidentsComponent implements OnInit {

  listIncidents : Incident[];
  year: number;
  month: number;
  actualMonth: string;
  months: string[];

  constructor(private service: IncidentService) { }

  ngOnInit(): void {
    this.months = ["ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"];
    this.getCurrentDate();
    this.findAll();
  }

  findAll(){
    this.service.findAll().subscribe(
      listIncidents => this.listIncidents = listIncidents
    );
  }

  getIncidents(){
    this.service.getIncidents(this.month, this.year).subscribe(
      listIncidents => this.listIncidents = listIncidents
    );
  }

  getCurrentDate(){
    var today = new Date();
    this.month = today.getMonth()+1;
    this.year = today.getFullYear();
    this.actualMonth = this.months[this.month-1];
  }

  public update(id: number){
    swal({
        title: 'Quieres resolver esta incidencia?',
        text: "",
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si'
      }).then((result) => {
        if (result.value) {
          this.updateService(id)
        }
      })

  }

  private updateService(id: number){
    this.service.updateIncident(id).subscribe(
      response => {
        if(response){
          location.replace('/listincidents');
        }
        else{
          swal("Error", `Error al actualizar incidencia`, 'error');
        }
      },
      err => {
        swal("Error", `Error al actualizar incidencia`, 'error');
      }
    )
  }

  public updateList(m: number){
    this.month = m;
    this.actualMonth = this.months[m-1];
    this.getIncidents();
  }

  public updateYear(year: number){
    this.year = year;
    this.getIncidents();
  }

}
