import { Component, OnInit, AfterViewInit } from '@angular/core';
import { IndexService } from './index.service';
import { ClienteMatricula } from '../dto/clientematricula';
import { ChartType, ChartOptions, ChartDataSets } from 'chart.js';
import { SingleDataSet, Label, Color, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  //llamamos a la capa de servicio para obtener los datos de las estadisticas

  estadisticasData :  Map<string, object>;
  lastUsers: Map<number, ClienteMatricula>;
  graphMap: Map<string, number>;
  graphMonth: Map <string, number>;

  // Pie
  public pieChartOptions: ChartOptions = {
    responsive: true,
  };
  //cargamos los labels y los datos de stats desde el service
  public pieChartLabels: Label[] = [];
  public pieChartData: SingleDataSet = [0,0,0,0,0];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];

  private array: number[] = [];

  //DATA lineal desde service tambien
  public lineChartData: ChartDataSets[] = [{label: "CLIENTES", data: this.array}];
  public lineChartLabels: Label[] = ['ENERO', 'FEBRERO', 'MARZO', 'ABRIL', 'MAYO', 'JUNIO', 'JULIO',
  'AGOSTO', 'SEPTIEMBRE', 'OCTUBRE', 'NOVIEMBRE', 'DICIEMBRE'];


  public lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,0,0,0.3)',
    },
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [];
  public lineChartOptions: (ChartOptions & { annotation: any }) = {
    annotation: true
  };

  constructor(private service: IndexService) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();

  }

  ngOnInit(): void {

    //check if logged // TODO:

      this.service.getData().subscribe(
        //asignamos
        estadisticasData => this.estadisticasData = estadisticasData,
        // The 2nd callback handles errors.
        (err) => console.error(err),
        // The 3rd callback handles the "complete" event.
        () => this.printStats(this.estadisticasData)
      );

      this.service.getClients().subscribe(
        //asignamos
        lastUsers => this.lastUsers = lastUsers
      );
    

  }

  printStats(mapa: Map<string,Object>): void{
    let fooInString = JSON.stringify(mapa);
    let output = JSON.parse(fooInString);
    this.graphMap = output.graphTarifas;
    this.graphMonth = output.graphMes;

    var num = 0;
    Object.keys(this.graphMap).forEach(key=>{
      this.pieChartLabels.push(key);
      this.pieChartData[num] = this.graphMap[key];
      num++;
    })


    Object.keys(this.graphMonth).forEach(key=>{
      if(key == 'ENERO'){
        this.array[0] = this.graphMonth[key];
      }
      if(key == 'FEBRERO'){
        this.array[1] = this.graphMonth[key];

      }
      if(key == 'MARZO'){
        this.array[2] = this.graphMonth[key];
        console.log(this.array[2]);
      }
      if(key == 'ABRIL'){
        this.array[3] = this.graphMonth[key];
      }
      if(key == 'MAYO'){
        this.array[4] = this.graphMonth[key];
      }
      if(key == 'JUNIO'){
        this.array[5] = this.graphMonth[key];
      }
      if(key == 'JULIO'){
        this.array[6] = this.graphMonth[key];
      }
      if(key == 'AGOSTO'){
        console.log(key, 'value: ', this.graphMonth[key]);
        this.array[7] = this.graphMonth[key];
      }
      if(key == 'SEPTIEMBRE'){
        this.array[8] = this.graphMonth[key];
      }
      if(key == 'OCTUBRE'){
        this.array[9] = this.graphMonth[key];
      }
      if(key == 'NOVIEMBRE'){
        this.array[10] = this.graphMonth[key];
      }
      if(key == 'DICIEMBRE'){
        this.array[11] = this.graphMonth[key];
      }
    })


  }




}
