import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';

export const routes: Routes = [

    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    // { path: 'patient', component: PatientDashboardComponent, canActivate: [AuthGuard] },
    // { path: 'doctor', component: DoctorDashboardComponent, canActivate: [AuthGuard] },
    // { path: 'admin', component: AdminDashboardComponent, canActivate: [AuthGuard] },
    { path: '', redirectTo: 'login', pathMatch: 'full' }
];
