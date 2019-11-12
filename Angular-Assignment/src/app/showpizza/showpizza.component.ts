
import { Component, OnInit, OnChanges, OnDestroy } from "@angular/core";
import { PosService } from '../service/app.posservice'
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";
@Component({
  selector: 'app-showpizza',
  templateUrl: './showpizza.component.html',
})
export class ShowpizzaComponent implements OnInit {


    
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
        if(!(sessionStorage.getItem('userRole') === "ROLE_ADMIN")){
            this.router.navigate(['forbidden']);
        }

        this.service.getStores().subscribe((storeListS: any[]) => this.storeList = storeListS);

    }


    onChangestore(storeId): any {
        this.storeId = storeId;
        this.service.getPizzas(storeId).subscribe((pizzaListS: any[]) => this.pizzaList = pizzaListS);

    }


   

    deletePizza(pizzaId): any {
        alert(pizzaId);
        this.service.deletePizza(pizzaId).subscribe(() => console.log());
        this.pizzaList = [];
        this.service.getPizzas(this.storeId).subscribe((pizzaListS: any[]) => this.pizzaList = pizzaListS);
    }

    updatePizza(): any {
        this.service.updatePizza(this.modelPizza, this.modelPizza.pizzaId).subscribe((data) => console.log(data));
        this.update = false;


    }

    



    initiateUpdatePizza(inid): any {
        this.update = true;
        for (var i = 0; i < this.pizzaList.length; i++) {
            if (inid == this.pizzaList[i].pizzaId) {
                this.modelPizza.pizzaId = this.pizzaList[i].pizzaId;
                this.modelPizza.pizzaName = this.pizzaList[i].pizzaName;
                this.modelPizza.pizzaDescription = this.pizzaList[i].pizzaDescription;
                this.modelPizza.pizzaCost = this.pizzaList[i].pizzaCost;

                

            }
        }

    }

}
