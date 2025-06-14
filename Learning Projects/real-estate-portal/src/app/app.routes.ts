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
import { SellerProfileComponent } from './seller/seller-profile/seller-profile.component';
import { ManagePropertyComponent } from './seller/manage-property/manage-property.component';
import { MeetingRequestComponent } from './seller/meeting-request/meeting-request.component';
import { BuyerHomeComponent } from './buyer/buyer-home/buyer-home.component';
import { BuyerFavoritesComponent } from './buyer/buyer-favorites/buyer-favorites.component';
import { BuyerProfileComponent } from './buyer/buyer-profile/buyer-profile.component';
import { BuyerRequestComponent } from './buyer/buyer-request/buyer-request.component';
import { BuyerSettingsComponent } from './buyer/buyer-settings/buyer-settings.component';
import { PropertyDetailsComponent } from './buyer/property-details/property-details.component';

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
      { path: '', redirectTo: 'seller-home', pathMatch: 'full' },
      { path: 'seller-home', component: SellerHomeComponent },
      { path: 'add-property', component: AddPropertyComponent },
      { path: 'seller-profile', component: SellerProfileComponent },
      { path: 'meeting-request', component: MeetingRequestComponent },
      { path: 'manage-property', component: ManagePropertyComponent },
    ],
  },
  {
    path: 'buyer-dashboard',
    component: BuyerDashboardComponent,
    data: { roles: ['buyer'] },
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'buyer-home', pathMatch: 'full' },
      { path: 'buyer-home', component: BuyerHomeComponent },
      { path: 'buyer-favorites', component: BuyerFavoritesComponent },
      { path: 'buyer-request', component: BuyerRequestComponent },
      { path: 'buyer-profile', component: BuyerProfileComponent },
      { path: 'buyer-settings', component: BuyerSettingsComponent },
      { path: 'property-details', component: PropertyDetailsComponent }
    ],
  },
  { path: '**', redirectTo: 'login', pathMatch: 'full' },
];
