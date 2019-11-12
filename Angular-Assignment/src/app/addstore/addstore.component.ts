import { Component, OnInit } from '@angular/core';
import {PosService} from '../service/app.posservice';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: 'app-addstore',
  templateUrl: './addstore.component.html'
})
export class AddstoreComponent implements OnInit {


  ngOnInit() {
    if(!(sessionStorage.getItem('userRole') === "ROLE_ADMIN")){
        this.router.navigate(['forbidden']);
    }
  }


    store:any = {};
    status:boolean=true;

    storeNameStatus=false;
    storeAddressStatus=false;
    lastNameStatus=false;
    storePhoneNumberStatus=false;
    // aadharNumberStatus=false;
    errorMessage:String='';

constructor(private service:PosService,private myhttp:HttpClient, private router:Router){
    console.log("In constructor");
}

addStore():any{
    console.log("In store register component");
    this.service.addStore(this.store).subscribe((body)=>console.log(body),error=>alert(error.error));
}


validate():any{

    if(this.store.storeName!='' && this.store.storeName.length>=5 && this.store.storeName.match("[a-zA-Z\\s]")){
        this.storeNameStatus=true;
        console.log("1");
    }

    if(this.store.storePhoneNumber!='' && this.store.storePhoneNumber.match("(0/91)?[7-9][0-9]{9}")){
        this.storePhoneNumberStatus=true;
        console.log("3");
    }
  
    if(this.storeNameStatus  && this.storePhoneNumberStatus){
        console.log("5");
        this.status=false;
    }
    
    else{
        this.status=true;
    }
        
    
}

}



