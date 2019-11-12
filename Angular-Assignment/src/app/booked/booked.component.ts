import { Component, OnInit } from '@angular/core';
import {PosService} from '../service/app.posservice';

import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";


@Component({
  selector: 'app-booked',
  templateUrl: './booked.component.html'
})
export class BookedComponent implements OnInit {
  pizza:any={};
  constructor(private myhttp:HttpClient, private router:Router) {
    alert(this.pizza.pizzaName);
    this.pizza=sessionStorage.getItem('pizza');
  }

  ngOnInit() {
    if(!(sessionStorage.getItem('userRole') === "ROLE_USER")){
      this.router.navigate(['forbidden']);
  }
  }
  
  

}
