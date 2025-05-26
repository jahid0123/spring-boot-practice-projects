import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { UserDashboardComponent } from './page/user/user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './page/admin/admin-dashboard/admin-dashboard.component';
import { ViewBookDetailsComponent } from './page/user/view-book-details/view-book-details.component';
import { authGuard } from './core/auth.guard';
import { UserHomeComponent } from './page/user/user-home/user-home.component';
import { AdminAddBookComponent } from './page/admin/admin-add-book/admin-add-book.component';
import { AdminHomeComponent } from './page/admin/admin-home/admin-home.component';
import { UserCartComponent } from './page/user/user-cart/user-cart.component';
import { FavouriteComponent } from './page/user/favourite/favourite.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },

 {
  path: 'user-dashboard',
  component: UserDashboardComponent,
   canActivate: [authGuard],
  children: [
    { path: '', redirectTo: 'user-home', pathMatch: 'full' }, // ✅ add this
    { path: 'user-home', component: UserHomeComponent },
    { path: 'view-details', component: ViewBookDetailsComponent },
    { path: 'cart', component: UserCartComponent },
    { path: 'favorite-list', component: FavouriteComponent },
    { path: '**', redirectTo: 'user-home'  }
  ]
},

  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [authGuard],
    children: [
    { path: '', redirectTo: 'admin-home', pathMatch: 'full' }, // ✅ add this
    { path: 'admin-home', component: AdminHomeComponent },
    { path: 'add-book', component: AdminAddBookComponent },
    {path: 'order-history', component: UserCartComponent},
    { path: '**', redirectTo: 'admin-home' }
  ]
    
  },

  

  { path: '**', redirectTo: 'login' }
];

