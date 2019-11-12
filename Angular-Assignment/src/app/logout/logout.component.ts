import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PosService } from "../service/app.posservice";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private service:PosService,
    private router: Router) {

  }

  ngOnInit() {
    this.service.logOut();
    this.router.navigate(['login']);
  }

}