import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { UserDashboardComponent } from './page/user/user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './page/admin/admin-dashboard/admin-dashboard.component';
import { ViewBookDetailsComponent } from './page/user/view-book-details/view-book-details.component';

export const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'signup', component: SignupComponent},
    {path: 'user-dashboard', component: UserDashboardComponent},
    {path: 'admin-dashboard', component: AdminDashboardComponent},
    {path: 'view-details', component: ViewBookDetailsComponent},
    {path: '**', redirectTo: 'login'}
];
