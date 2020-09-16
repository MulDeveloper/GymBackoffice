import { Component, OnInit } from '@angular/core';
import { LoginService } from './service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username:string='';
  password:string='';

  constructor(private service: LoginService) { }

  ngOnInit(): void {
  }

  public login(){
      this.service.log(this.username, this.password);
  }

}
