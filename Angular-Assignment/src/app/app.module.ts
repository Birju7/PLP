import {NgModule }      from '@angular/core';
import {BrowserModule } from '@angular/platform-browser';
import {AppComponent }  from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatExpansionModule, MatInputModule, MatCardModule, MatButtonModule, MAT_DIALOG_DEFAULT_OPTIONS} from '@angular/material'



// import { CustomerhomeComponent } from './customerhome/customerhome.component';




import { DatePipe } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';


// import { CustomerHomeComponent } from './app.customerhomecomponent';

import { RegisterComponent } from './register/register.component';
import {FileUploadModule} from 'ng2-file-upload';
import { ExcelUploadComponent } from './app.exceluploadcomponent';

// import { LoginComponent } from './app.login';
import { LoginComponent } from './login/login.component';
import { CustomerhomeComponent } from './customerhome/customerhome.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { UploadstoresComponent } from './uploadstores/uploadstores.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { LogoutComponent } from './logout/logout.component';
import { AddpizzaComponent } from './addpizza/addpizza.component';
import { AddstoreComponent } from './addstore/addstore.component';
import { ShowstoreComponent } from './showstore/showstore.component';
import { ShowpizzaComponent } from './showpizza/showpizza.component'
;
// import { ConfirmbookingComponent } from './confirmbooking/confirmbooking.component' ;
import { BookedComponent } from './booked/booked.component';
import { ContactusComponent } from './contactus/contactus.component';

import { AboutusComponent } from './aboutus/aboutus.component';;
import { ForbiddenComponent } from './forbidden/forbidden.component'
;
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
const myroute: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    { path: 'logout', component: LogoutComponent },
    { path: 'addstore', component: AddstoreComponent },
    { path: 'addpizza', component: AddpizzaComponent },
    { path: 'showstore', component: ShowstoreComponent },
    { path: 'showpizza', component: ShowpizzaComponent },
    { path: 'app', component: AppComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'adminhome', component: AdminhomeComponent },
    { path: 'uploadstore', component: ExcelUploadComponent },
    { path: 'customerhome', component: CustomerhomeComponent },
    { path: 'forbidden', component: ForbiddenComponent },
    { path: 'booked', component: BookedComponent },
    { path: '**', component: PagenotfoundComponent }
    // { path: 'confirmbooking', component: ConfirmbookingComponent },
    

]

@NgModule({
    imports: [
        BrowserModule, FormsModule, HttpClientModule, BrowserAnimationsModule,
        MatExpansionModule, MatInputModule, MatCardModule, MatButtonModule, FileUploadModule,
        RouterModule.forRoot(myroute)
        
        
    ],
    declarations: [
        AppComponent, HomeComponent,  CustomerhomeComponent, 
        LoginComponent,  RegisterComponent, ExcelUploadComponent
, CustomerhomeComponent	, AdminhomeComponent, UploadstoresComponent, ShowpizzaComponent, ShowstoreComponent,
AddstoreComponent, AddpizzaComponent, LogoutComponent,  SignupComponent, BookedComponent, AboutusComponent, ContactusComponent, ForbiddenComponent, PagenotfoundComponent],
    providers: [ DatePipe,  {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}],
    
    bootstrap: [AppComponent]
})

export class AppModule { }