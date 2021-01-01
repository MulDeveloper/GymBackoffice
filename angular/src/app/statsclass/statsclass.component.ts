import { Component, OnInit } from '@angular/core';
import { ClassesService } from '../gymclass/classes.service';
import { ChartType, ChartOptions, ChartDataSets } from 'chart.js';
import { SingleDataSet, Label, Color, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';

@Component({
  selector: 'app-statsclass',
  templateUrl: './statsclass.component.html'
})
export class StatsclassComponent implements OnInit {

  stats :  Map<string, number>;

  public pieChartData: SingleDataSet = [0,0,0,0,0];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];
  public pieChartLabels: Label[] = [];
  // Pie
  public pieChartOptions: ChartOptions = {
    responsive: true,
  };


  constructor(private classesService: ClassesService) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
  }

  ngOnInit(): void {
    this.classesService.getStatsClass().subscribe(
      stats => this.stats = stats,
      // The 2nd callback handles errors.
      (err) => console.error(err),
      // The 3rd callback handles the "complete" event.
      () => this.printStats(this.stats)
    );
  }

  printStats(mapa: Map<string,number>): void{
    var num = 0;
    Object.keys(this.stats).forEach(key=>{
      this.pieChartLabels.push(key);
      this.pieChartData[num] = this.stats[key];
      num++;
    })

  }

}
