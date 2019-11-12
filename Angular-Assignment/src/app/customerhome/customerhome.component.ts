import { Component, OnInit, OnChanges, OnDestroy } from "@angular/core";
import { PosService } from '../service/app.posservice'
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: 'app-customerhome',
  templateUrl: './customerhome.component.html',

})
export class CustomerhomeComponent implements OnInit {



    
    modelPizza: any = {};
    update= false;

    storeList: any[] = [];
    pizzaList: any[] = [];
    roomList: any[] = [];
    storeId : any;
    constructor(private service: PosService,private myhttp:HttpClient, private router:Router) {

        console.log("In constructor");
    }

    ngOnInit(): void {
        if(!(sessionStorage.getItem('userRole') === "ROLE_USER")){
            this.router.navigate(['forbidden']);
        }

        this.service.getStores().subscribe((storeListS: any[]) => this.storeList = storeListS);

    }


    onChangestore(storeId): any {
        this.storeId = storeId;
        this.service.getPizzas(storeId).subscribe((pizzaListS: any[]) => this.pizzaList = pizzaListS);

    }


   


    buyPizza(pizza): any {
        sessionStorage.setItem("pizza",pizza);
        alert(this.modelPizza);
        this.service.buyPizza(pizza).subscribe((data) => console.log(data));
        this.update = false;
        


    }

    




}

