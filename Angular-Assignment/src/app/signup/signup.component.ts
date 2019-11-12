import { Component, OnInit } from '@angular/core';
import { Customer } from '../_model/app.customer';
import { Router } from '@angular/router';
import { PosService } from '../service/app.posservice'
// import { AuthenticationService } from '../_service/app.authenticationservice';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
  // selector: 'signup',
  //   templateUrl: '/_pages/app.signup.html',
  //   styleUrls: ['../assets/css/signup.css']
})
export class SignupComponent implements OnInit {


    
    customer:any;
    public barLabel: string = "Password strength:";
    public myColors = ['#DD2C00', '#FF6D00', '#FFD600', '#AEEA00', '#00C853'];

    constructor(private router: Router, private hcsservice: PosService){}//, private loginservice: AuthenticationService

    ngOnInit(){
        // if(sessionStorage.getItem('role')==='user' || sessionStorage.getItem('role')==='admin'){
        //     this.router.navigate(['noauth']);
        // }
    }

    //Adds a new user
    signUp(){
        console.log(this.customer);
        
        // this.loginservice.signUp(this.customer).subscribe();
        // this.router.navigate(["login"]);
    }

    //Validation
    nameFlag:boolean=false;
    validateName(){
        var flag = /^[a-zA-Z ]+$/.test(this.customer.username);
        if(!flag){
            this.nameFlag=true;
        }else{
            this.nameFlag=false;
        }
    }

    //Validation
    phoneFlag:boolean=false;
    validatePhone(){
        let phone=String(this.customer.userMobile);
        if(phone.length!=10){
            this.phoneFlag=true;
        }else{
            this.phoneFlag=false;
        }
    }

    //Validation
    emailIdFlag:boolean=false;
    validateemailId(){
        var flag=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.customer.emailId);
        if(!flag){
            this.emailIdFlag=true;
        }else{
            this.emailIdFlag=false;
        }
    }

    buttonFlag:boolean=true;
    enableButton(){
        this.buttonFlag=this.nameFlag||this.emailIdFlag||this.phoneFlag;
    }

}
