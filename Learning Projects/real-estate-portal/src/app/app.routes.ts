import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { BuyerRegisterComponent } from './auth/signup/buyer-register/buyer-register.component';
import { SellerRegisterComponent } from './auth/signup/seller-register/seller-register.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { SellerDashboardComponent } from './seller/seller-dashboard/seller-dashboard.component';
import { BuyerDashboardComponent } from './buyer/buyer-dashboard/buyer-dashboard.component';
import { AddPropertyComponent } from './seller/add-property/add-property.component';
import { SellerHomeComponent } from './seller/seller-home/seller-home.component';
import { authGuard } from './core/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'buyer-register', component: BuyerRegisterComponent },
  { path: 'seller-register', component: SellerRegisterComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { 
    path: 'seller-dashboard', 
    component: SellerDashboardComponent,
    data: { roles: ['seller'] },
    canActivate: [authGuard],
    children: [
        {path: '', redirectTo: 'seller-home', pathMatch: 'full'},
        {path: 'seller-home', component: SellerHomeComponent},
        {path: 'add-property', component: AddPropertyComponent}
    ]
 },
  { 
    path: 'buyer-dashboard', 
    component: BuyerDashboardComponent,
    data: { roles: ['buyer'] },
    canActivate: [authGuard],
    children: [
       
    ]
 },
  { path: '**', redirectTo: 'login', pathMatch: 'full' },
];
