import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
  OnInit
} from '@angular/core';
import {startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
  setHours,
  setMinutes
} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView
} from 'angular-calendar';

import { FormGroup, FormBuilder } from '@angular/forms';

import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';

import {Gymclass} from '../dto/gymclass';
import {Personal} from '../dto/personal';
import {ClassesService} from './classes.service';
import { DatePipe } from '@angular/common';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  violet: {
    primary: '#A508E3',
    secondary: '#C694CA',
  },
  orange: {
    primary: '#FFAE00',
    secondary: '#FFD06C',
  },
  green: {
    primary: '#01773E',
    secondary: '#30FF9B',
  }
};

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-gymclass',
  templateUrl: './gymclass.component.html',
  styleUrls: ['./gymclass.component.css']
})
export class GymclassComponent {
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Week;

  CalendarView = CalendarView;

  newEvent: FormGroup;

  gymclass: Gymclass = new Gymclass();

  viewDate: Date = new Date();

  gymclasses: string[] = ['CROSSFIT','BODY-PUMP','ZUMBA','HALTEROFILIA','CYCLING'];
  staff: Personal[];

  modalData: {
    action: string;
    event: CalendarEvent;

  };

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.service.delGymClass(''+event.id).subscribe();
      },
    },
  ];

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  schedule: Gymclass[];

  clickedDate: Date;

  currentDate: Date;

  activeDayIsOpen: boolean = true;

  constructor(private modal: NgbModal,
    private service: ClassesService,
    public datepipe: DatePipe,
    private fb: FormBuilder) {
    registerLocaleData(localeEs);
    this.newEvent = this.fb.group({
     description: [''],
     staff: [''],
     timeClass: [''],
     duration: [''],
     intensity: ['']
    });

  }

  ngOnInit(): void{
    this.getGymClasses();
    this.getStaff();
    this.currentDate = new Date();

  }

  getStaff(){
    //we call the service and populate the staff
    this.service.getStaffByRoleMonitor().subscribe(
      staff => this.staff = staff
    );


  }

  getGymClasses(){

    var mondayDate = this.getMonday(new Date());
    var sunday = this.getMonday(new Date());
    sunday.setDate(sunday.getDate() + 7);
    var from = this.datepipe.transform(mondayDate, 'yyyy-MM-dd');
    var to = this.datepipe.transform(sunday, 'yyyy-MM-dd');

    //we call the service and populate the classes
    this.service.getListClasses(from, to).subscribe(
      schedule => this.schedule = schedule,
      error => console.log('error'),
      () => this.populate()
    );


  }

  newClass(createEvent, gymclass){
    this.newEventModal(createEvent, gymclass);
  }



  public populate(){
    this.schedule.forEach( (c) => {
      var date = new Date(c.dateClass);
      var time = c.timeClass.split(':');
      var color;
      switch(c.description) {
       case 'HALTEROFILIA': {
          color = colors.red;
          break;
       }
       case 'ZUMBA': {
          color = colors.blue;
          break;
       }
       case 'CYCLING': {
          color = colors.violet;
          break;
       }
       case 'CROSSFIT': {
          color = colors.orange;
          break;
       }
       case 'BODY-PUMP': {
          color = colors.green;
          break;
       }
    }

        this.events.push(
          {
            id: c.idclass,
            title: c.description,
            start: setHours(date.setMinutes(+time[1]),+time[0]),
            end: setMinutes(date.setHours(+time[0],0,0),(+time[1] + (+c.duration))),
            color: color,
            actions: this.actions,
          }
        )
    });

    this.refresh.next();


  }


  public newEventModal(m, gymclass: Gymclass){
      this.modal.open(m, {
       centered: true,
       backdrop: 'static'
      });


      this.newEvent.patchValue({
        description: gymclass.description,
        staff: gymclass.staff,
        duration: gymclass.duration,
        intensity: gymclass.intensity
      });
    }


  addEvent(gymclass: Gymclass): void {
    var color;
    switch(gymclass.description) {
     case 'HALTEROFILIA': {
        color = colors.red;
        break;
     }
     case 'ZUMBA': {
        color = colors.blue;
        break;
     }
     case 'CYCLING': {
        color = colors.violet;
        break;
     }
     case 'CROSSFIT': {
        color = colors.orange;
        break;
     }
     case 'BODY-PUMP': {
        color = colors.green;
        break;
     }
  }
    //we create a new event
    this.events = [
      ...this.events,
      {
        title: gymclass.description,
        start: this.clickedDate,
        end: setMinutes(this.clickedDate,+gymclass.duration),
        color: color,
        actions: this.actions
      },
    ];

    //we persist the data calling the backend API

    gymclass.timeClass = this.clickedDate.getHours()+':'+this.clickedDate.getMinutes();
    gymclass.clients = new Array();
    var d = this.datepipe.transform(this.clickedDate, 'yyyy-MM-dd');
    gymclass.dateClass = new Date(d);

    var res = this.service.addGymClass(gymclass).subscribe();

    this.modal.dismissAll();
    location.reload();

  }


  setView(view: CalendarView) {
    this.view = view;
  }

  thisWeek() {
    this.activeDayIsOpen = true;
    //get date
    this.currentDate = new Date();
  }


  nextWeek() {
    this.activeDayIsOpen = false;
    //get date
    this.currentDate = new Date();
    this.currentDate.setDate(this.currentDate.getDate() + 7);
  }

  previousWeek() {
    this.activeDayIsOpen = false;
    this.currentDate = new Date();
    this.currentDate.setDate(this.currentDate.getDate() - 7);
  }


  copyWeek(){
    var mondayDate = this.getMonday(this.currentDate);
    var from = this.datepipe.transform(mondayDate, 'yyyy-MM-dd').toString();
    this.service.copyWeek(from).subscribe();

  }

  getMonday(d) {
    d = new Date(d);
    var day = d.getDay(),
        diff = d.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
    return new Date(d.setDate(diff));
  }

  onSubmit(){
    var gymClassNew = new Gymclass(this.newEvent.getRawValue());
    this.addEvent(gymClassNew);
  }

}
