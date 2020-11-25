import { Component, OnInit } from '@angular/core';
import { LoginService } from './service/login.service';
import { UserLogin } from '../dto/userlogin';

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
      var user: UserLogin = new UserLogin(this.username, this.password);
      this.service.log(user);
  }

}
