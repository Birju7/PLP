import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html'
})
export class AdminhomeComponent implements OnInit {

  constructor( private myhttp:HttpClient, private router:Router) { }

  ngOnInit() {
    if (!(sessionStorage.getItem('userRole') === "ROLE_ADMIN")) {
      this.router.navigate(['forbidden']);
  }
  }

}
