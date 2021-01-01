import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Membership } from '../dto/membership';
import { Prices } from '../dto/prices';
import {MembershipsService} from './memberships.service';
import swal from 'sweetalert2';


@Component({
  selector: 'app-membership',
  templateUrl: './membership.component.html'
})
export class MembershipComponent implements OnInit {

  id: number;
  membership: Membership;
  prices: Prices[];
  months: number;



  constructor(private service: MembershipsService,
  private router : Router,
  private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = + this.route.snapshot.paramMap.get('id');
    this.getMembership();
    this.getPrices();
  }

  getPrices(){
    this.service.getListPrices().subscribe(
      prices => this.prices = prices
    );
  }

  getMembership(){
    this.service.getOne(this.id).subscribe(
      membership => this.membership = membership
    );
  }

  public save(){
    if(this.months != null){
      var resp = this.service.update(this.membership, this.months).subscribe();
      if(resp){
        location.replace('/listclients');
      }
      else{
        swal("Error", `Error al modificar registro`, 'error');
      }
    }
    else{
      this.service.update(this.membership, 0).subscribe();
      location.replace('/listclients');
    }


  }





}
