import { Component, OnInit } from '@angular/core';
import {PosService} from '../service/app.posservice';
import { Router } from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})



export class RegisterComponent implements OnInit {


  ngOnInit() {
  }


    customer:any = {};
    status:boolean=true;

    usernameStatus=false;
    passwordStatus=false;
    firstNameStatus=false;
    lastNameStatus=false;
    userMobileStatus=false;
    errorMessage:String='';

constructor(private service:PosService,private router:Router){
    console.log("In constructor");
}

register():any{
    console.log("In register component");
    this.service.register(this.customer).subscribe((body)=>console.log(body));
}


validate():any{

    if(this.customer.username!='' && this.customer.username.length>=5 && this.customer.username.match("[a-zA-Z\\s]")){
        this.usernameStatus=true;
        console.log("1");
    }
    if(this.customer.password!='' && this.customer.password.length>=5){
        this.passwordStatus=true;
        console.log("2");
    }
    if(this.customer.userMobile!='' && this.customer.userMobile.match("(0/91)?[7-9][0-9]{9}")){
        this.userMobileStatus=true;
        console.log("3");
    }
    
    if(this.usernameStatus && this.passwordStatus && this.userMobileStatus){
        console.log("5");
        this.status=false;
    }
    
    else{
        this.status=true;
    }
        
    
}

}


