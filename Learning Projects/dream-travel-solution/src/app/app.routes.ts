import { Routes } from '@angular/router';
import { RegisterComponent } from './authcomponent/register/register.component';
import { LoginComponent } from './authcomponent/login/login.component';
import { HeaderComponent } from './section/header/header.component';
import { HomeComponent } from './page/home/home.component';

export const routes: Routes = [

    {path: 'home', component: HomeComponent},
    {path: 'header', component: HeaderComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: '**', redirectTo: 'home'}

];
