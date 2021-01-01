import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { IndexComponent } from './index/index.component';
import { ChartsModule } from 'ng2-charts';
import { PersonalComponent } from './personal/addpersonal/personal.component';
import { MenuComponent } from './menu/menu.component';
import { ListapersonalComponent } from './personal/listapersonal/listapersonal.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EditpersonalComponent } from './personal/editpersonal/editpersonal.component';
import { EditpassComponent } from './personal/editpersonal/editpass/editpass.component';
import { ListclientComponent } from './cliente/listclient/listclient.component';
import { AddclientComponent } from './cliente/addclient/addclient.component';
import { MembershipComponent } from './membership/membership.component';
import { ShowclientComponent } from './cliente/showclient/showclient.component';
import { ListincidentsComponent } from './incident/listincidents/listincidents.component';
import { CreateComponent } from './incident/create/create.component';

import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {AuthInterceptor} from './interceptor/AuthInterceptor';

import {AuthGuard} from './auth.guard';
import { GymclassComponent } from './gymclass/gymclass.component';

import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { FlatpickrModule } from 'angularx-flatpickr';
import { DatePipe } from '@angular/common';
import { ClientstatsComponent } from './clientstats/clientstats.component';
import { StatsclassComponent } from './statsclass/statsclass.component';

const routes: Routes = [

  {path: '', redirectTo:'/login', pathMatch:'full'},
  {path: 'login', component: LoginComponent},
  {path: 'index', component: IndexComponent, canActivate: [AuthGuard]},
  {path: 'altapersonal', component: PersonalComponent, canActivate: [AuthGuard]},
  {path: 'listapersonal', component: ListapersonalComponent, canActivate: [AuthGuard]},
  {path: 'editpersonal', component: EditpersonalComponent, canActivate: [AuthGuard]},
  {path: 'editpass', component: EditpassComponent, canActivate: [AuthGuard]},
  {path: 'listclients', component: ListclientComponent, canActivate: [AuthGuard]},
  {path: 'addclient', component: AddclientComponent, canActivate: [AuthGuard]},
  {path: 'addincident', component: CreateComponent, canActivate: [AuthGuard]},
  {path: 'membership/:id', component: MembershipComponent, canActivate: [AuthGuard]},
  {path: 'client/:id', component: ShowclientComponent, canActivate: [AuthGuard]},
  {path: 'listincidents', component: ListincidentsComponent, canActivate: [AuthGuard]},
  {path: 'classes', component: GymclassComponent, canActivate: [AuthGuard]},
  {path: 'clientstats', component: ClientstatsComponent, canActivate: [AuthGuard]},
  {path: 'statsclass', component: StatsclassComponent, canActivate: [AuthGuard]}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    IndexComponent,
    PersonalComponent,
    MenuComponent,
    ListapersonalComponent,
    EditpersonalComponent,
    EditpassComponent,
    ListclientComponent,
    AddclientComponent,
    MembershipComponent,
    ShowclientComponent,
    ListincidentsComponent,
    CreateComponent,
    GymclassComponent,
    ClientstatsComponent,
    StatsclassComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    ChartsModule,
    NgbModule,
    ReactiveFormsModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),

  ],
  providers: [
    DatePipe,
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
