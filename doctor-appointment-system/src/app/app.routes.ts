import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { authGuard } from './auth/guard/auth.guard';
import { DoctorDashboardComponent } from './dashboard/doctor/doctor-dashboard/doctor-dashboard.component';
import { AdminDashboardComponent } from './dashboard/admin/admin-dashboard/admin-dashboard.component';
import { PatientDashboardComponent } from './dashboard/patient/patient-dashboard/patient-dashboard.component';
import { AdminHomeComponent } from './dashboard/admin/admin-home/admin-home.component';
import { DoctorHomeComponent } from './dashboard/doctor/doctor-home/doctor-home.component';
import { PatientHomeComponent } from './dashboard/patient/patient-home/patient-home.component';

export const routes: Routes = [
  // { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  // { path: '', redirectTo: 'login', pathMatch: 'full' }

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'patient',
    component: PatientDashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'patient-home', pathMatch: 'full' }, // ✅ add this
      { path: 'patient-home', component: PatientHomeComponent },
    ],
  },
  {
    path: 'doctor',
    component: DoctorDashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'doctor-home', pathMatch: 'full' }, // ✅ add this
      { path: 'doctor-home', component: DoctorHomeComponent },
    ],
  },
  {
    path: 'admin',
    component: AdminDashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'admin-home', pathMatch: 'full' }, // ✅ add this
      { path: 'admin-home', component: AdminHomeComponent },
    ],
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' },
];
