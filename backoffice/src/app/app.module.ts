import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { IndexComponent } from './index/index.component';
import { ChartsModule } from 'ng2-charts';
import { PersonalComponent } from './personal/addpersonal/personal.component';
import { MenuComponent } from './menu/menu.component';
import { ListapersonalComponent } from './personal/listapersonal/listapersonal.component';

const routes: Routes = [
  {path: '', redirectTo:'/login', pathMatch:'full'},
  {path: 'login', component: LoginComponent},
  {path: 'index', component: IndexComponent},
  {path: 'altapersonal', component: PersonalComponent},
  {path: 'listapersonal', component: ListapersonalComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    IndexComponent,
    PersonalComponent,
    MenuComponent,
    ListapersonalComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    ChartsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
