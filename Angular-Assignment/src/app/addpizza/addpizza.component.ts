import { Component, OnInit } from '@angular/core';
import {PosService} from '../service/app.posservice';

import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";



@Component({
  selector: 'app-addpizza',
  templateUrl: './addpizza.component.html'
})
export class AddpizzaComponent implements OnInit {



    pizza:any = {};
    status:boolean=true;

    pizzaNameStatus=false;
    pizzaDescriptionStatus=false;
    // lastNameStatus=false;
    pizzaCostStatus=false;
    // aadharNumberStatus=false;
    errorMessage:String='';

    storeList: any[] = [];
    constructor(private service:PosService,private myhttp:HttpClient, private router:Router){
        console.log("In constructor");
    }
  ngOnInit() {
    if(!(sessionStorage.getItem('userRole') === "ROLE_ADMIN")){
        this.router.navigate(['forbidden']);
    }
    this.service.getStores().subscribe((storeListS: any[]) => this.storeList = storeListS);
    console.log(this.storeList);
  }




addPizza(storeId):any{
    console.log("In register component");
    this.service.addPizza(this.pizza, storeId).subscribe((body)=>console.log(body),error=>alert(error.error));
}


validate():any{

    if(this.pizza.pizzaName!='' && this.pizza.pizzaName.length>=5 && this.pizza.pizzaName.match("[a-zA-Z\\s]")){
        this.pizzaNameStatus=true;
        console.log("1");
    }
    // if(this.pizza.password!='' && this.pizza.password.length>=5){
    //     this.passwordStatus=true;
    //     console.log("2");
    // }
    if(this.pizza.pizzaCost!='' && this.pizza.pizzaCost.match("[1-9][0-9]{1,4}")){
        this.pizzaCostStatus=true;
        console.log("3");
    }

    if(this.pizzaNameStatus  && this.pizzaCostStatus){
        console.log("5");
        this.status=false;
    }
    
    else{
        this.status=true;
    }
        
    
}

}




