import { Component, OnInit } from '@angular/core';
import { PosService } from "../service/app.posservice";
import { Router } from '@angular/router';
import { Customer } from "../_model/app.customer";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']

  // selector: 'login',
  // templateUrl: 'app.login.html'
})
export class LoginComponent implements OnInit {


  model: any = [];
  useremail: any;
  password: any;
  invalidLogin = false;
  message:string;

  constructor(private router: Router,
    private hcsservice: PosService) { }

  ngOnInit() {
  }

  checkLogin() {
    console.log("Inside login.ts checkLogin.. email: " + this.useremail + " password: " + this.password)
    this.hcsservice.authenticate(this.useremail, this.password).subscribe(
      userData => {
        sessionStorage.setItem('username', this.useremail);
        let tokenStr = 'Bearer ' + userData.token;
        sessionStorage.setItem('token', tokenStr);
        this.hcsservice.getUser(this.useremail).subscribe((data: Customer) => {
          this.model = data;
          sessionStorage.setItem('userRole', data.role);
          sessionStorage.setItem('userId', data.userId);
          sessionStorage.setItem('userName', data.username)
        
          this.checkRoles();
        });
      },
      error => {
        alert("Invalid Credentials  ")
      });
    }


  checkRoles(){

    if (sessionStorage.getItem('userRole') === "ROLE_USER") {
   
      this.router.navigate(['/customerhome']).then(()=>{window.location.reload();});
    } 
    else if(sessionStorage.getItem('userRole') === "ROLE_ADMIN"){
      
      this.router.navigate(['/adminhome']).then(()=>{window.location.reload();});
    }
    
  }
}
