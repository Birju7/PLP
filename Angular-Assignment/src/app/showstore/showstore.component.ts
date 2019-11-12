import { Component, OnInit } from '@angular/core';
import { PosService } from '../service/app.posservice'
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: 'app-showstore',
  templateUrl: './showstore.component.html'
})
export class ShowstoreComponent implements OnInit {

  
    
    modelStore: any = {};
    update= false;

    cityList: any[] = [];
    storeList: any[] = [];
    roomList: any[] = [];
    cityId : any;
    constructor(private service: PosService,private myhttp:HttpClient, private router:Router) {

        console.log("In constructor");
    }

    ngOnInit(): void {
      this.service.getStores().subscribe((storeListS: any[]) => this.storeList = storeListS);
      if(!(sessionStorage.getItem('userRole') === "ROLE_ADMIN")){
        this.router.navigate(['forbidden']);
    }

    }
  


   

    deleteStore(storeId): any {
        alert("Deleteing store with store Id"+storeId);
        this.service.deleteStore(storeId).subscribe(() => console.log());
        
    }

    updateStore(): any {
        this.service.updateStore(this.modelStore, this.modelStore.storeId).subscribe((data) => console.log(data));
        this.update = false;


    }

    



    initiateUpdateStore(inid): any {
        this.update = true;
        for (var i = 0; i < this.storeList.length; i++) {
            if (inid == this.storeList[i].storeId) {
                this.modelStore.storeId = this.storeList[i].storeId;
                this.modelStore.storeName = this.storeList[i].storeName;
                this.modelStore.storeAddress = this.storeList[i].storeAddress;
                this.modelStore.storePhoneNumber = this.storeList[i].storePhoneNumber;
                

            }
        }

    }

}