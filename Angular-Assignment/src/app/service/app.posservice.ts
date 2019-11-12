import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
    providedIn: 'root'

})
export class PosService {
    constructor(private myhttp: HttpClient) {
    }

    register(data:any){
        return this.myhttp.post("http://localhost:9088/register",data);
    }

    addStore(data:any){
        return this.myhttp.post("http://localhost:9088/admin/stores",data);
    }


    updateStore(data: any, storeId: any) {
        alert("Store Updated , Please Refresh");
        return this.myhttp.put("http://localhost:9088/admin/stores?storeId=" + storeId, data);
    }

    deleteStore(storeId: any) {
        alert("Store Deleted , Please Refresh");
        return this.myhttp.delete("http://localhost:9088/admin/stores/" + storeId);
    }

    getStores() {
        return this.myhttp.get("http://localhost:9088/admin/stores");
    }



   


    addPizza(data: any, storeId: any) {
        alert("Pizza Added");
        return this.myhttp.post("http://localhost:9088/admin/pizzas?storeId=" + storeId, data);
    }

    updatePizza(data: any, pizzaId: any) {
        alert("Pizza Updated , Please Refresh");
        return this.myhttp.put("http://localhost:9088/admin/pizzas?pizzaId=" + pizzaId, data);
    }

    deletePizza(pizzaId: any) {
        alert("Pizza Deleted , Please Refresh");
        return this.myhttp.delete("http://localhost:9088/admin/pizzas/" + pizzaId);
    }

    getPizzas(storeId) {
        return this.myhttp.get("http://localhost:9088/admin/pizzas/" + storeId);
    }


    buyPizza(data: any) {
        alert("Pizza Purchased, Please Refresh");
        return this.myhttp.post("http://localhost:9088/customer/makecart?username=" + sessionStorage.getItem('username'), data);
    }






    getAllData() {
        return this.myhttp.get("http://localhost:9088/product/getall");
    }


    getUser(useremail:any){
        return this.myhttp.get("http://localhost:9088/admin/finduser?userEmail="+useremail);
    }

    

    // register(data:any){
    //     return this.myhttp.post("http://localhost:9088/customer/register",data);
    // }




    authenticate(username:string, password:string) {
        console.log("Inside service authenticate.. email: "+username+" password: "+password);
        const reqbody={emailId: username, password:password};
        console.log(JSON.stringify(reqbody))
        
        return this.myhttp.post<any>('http://localhost:9088/authenticate', {emailId: username, password:password});
      }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('username')
        console.log(!(user === null))
        return !(user === null)
    }

    logOut() {
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('userId');
        sessionStorage.removeItem('role');
       
    }

}