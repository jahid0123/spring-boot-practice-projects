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
import { SignupComponent } from './auth/admin/signup/signup.component';
import { AddDoctorComponent } from './dashboard/admin/add-doctor/add-doctor.component';
import { PatientAppointmentComponent } from './dashboard/patient/patient-appointment/patient-appointment.component';
import { PatientFavoriteComponent } from './dashboard/patient/patient-favorite/patient-favorite.component';
import { PrescriptionComponent } from './dashboard/doctor/prescription/prescription.component';
import { DoctorSettingsComponent } from './dashboard/doctor/doctor-settings/doctor-settings.component';
import { DoctorProfileComponent } from './dashboard/doctor/doctor-profile/doctor-profile.component';
import { DoctorAppointmentComponent } from './dashboard/doctor/doctor-appointment/doctor-appointment.component';

export const routes: Routes = [
  // { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  // { path: '', redirectTo: 'login', pathMatch: 'full' }

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'signup', component: SignupComponent},
  {
    path: 'patient',
    component: PatientDashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'patient-home', pathMatch: 'full' }, // ✅ add this
      {path: 'favorite', component: PatientFavoriteComponent},
      {path: 'appointment', component: PatientAppointmentComponent},
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
      {path: 'prescription', component: PrescriptionComponent},
      {path: 'doctor-appointment', component: DoctorAppointmentComponent},
      {path: 'doctor-settings', component: DoctorSettingsComponent},
      {path: 'doctor-profile', component: DoctorProfileComponent}
    ],
  },
  {
    path: 'admin',
    component: AdminDashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'admin-home', pathMatch: 'full' }, // ✅ add this
      { path: 'admin-home', component: AdminHomeComponent },
      {path: 'add-doctor', component: AddDoctorComponent},
    ],
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' },
];
