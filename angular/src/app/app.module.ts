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

const routes: Routes = [
  {path: '', redirectTo:'/login', pathMatch:'full'},
  {path: 'login', component: LoginComponent},
  {path: 'index', component: IndexComponent},
  {path: 'altapersonal', component: PersonalComponent},
  {path: 'listapersonal', component: ListapersonalComponent},
  {path: 'editpersonal', component: EditpersonalComponent},
  {path: 'editpass', component: EditpassComponent},
  {path: 'listclients', component: ListclientComponent}
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
    ListclientComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    ChartsModule,
    NgbModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
