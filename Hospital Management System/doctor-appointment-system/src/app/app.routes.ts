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
import { MyAppointmentComponent } from './dashboard/patient/my-appointment/my-appointment.component';
import { PatientProfileComponent } from './dashboard/patient/patient-profile/patient-profile.component';
import { PatientSettingsComponent } from './dashboard/patient/patient-settings/patient-settings.component';
import { ViewPrescriptionComponent } from './dashboard/patient/view-prescription/view-prescription.component';
import { ManageUsersComponent } from './dashboard/admin/manage-users/manage-users.component';
import { ManageAppointmentsComponent } from './dashboard/admin/manage-appointments/manage-appointments.component';
import { ManageBedComponent } from './dashboard/admin/manage-bed/manage-bed.component';
import { ManageDoctorComponent } from './dashboard/admin/manage-doctor/manage-doctor.component';
import { AdmissionPatientComponent } from './dashboard/admin/admission-patient/admission-patient.component';
import { AdmittedPatientComponent } from './dashboard/admin/admited-patient/admited-patient.component';
import { BillComponent } from './dashboard/admin/bill/bill.component';
import { DischargePatientComponent } from './dashboard/admin/discharge-patient/discharge-patient.component';

export const routes: Routes = [
  // { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  // { path: '', redirectTo: 'login', pathMatch: 'full' }

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'signup', component: SignupComponent },
  {
    path: 'patient',
    component: PatientDashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'patient-home', pathMatch: 'full' }, // ✅ add this
      { path: 'favorite', component: PatientFavoriteComponent },
      { path: 'appointment', component: PatientAppointmentComponent },
      { path: 'view-prescription', component: ViewPrescriptionComponent },
      { path: 'my-appointment', component: MyAppointmentComponent },
      { path: 'patient-profile', component: PatientProfileComponent },
      { path: 'patient-settings', component: PatientSettingsComponent },
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
      { path: 'prescription', component: PrescriptionComponent },
      { path: 'doctor-appointment', component: DoctorAppointmentComponent },
      { path: 'view-prescription', component: ViewPrescriptionComponent },
      { path: 'doctor-settings', component: DoctorSettingsComponent },
      { path: 'doctor-profile', component: DoctorProfileComponent },
    ],
  },
  {
    path: 'admin',
    component: AdminDashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'admin-home', pathMatch: 'full' }, // ✅ add this
      { path: 'admin-home', component: AdminHomeComponent },
      { path: 'add-doctor', component: AddDoctorComponent },
      { path: 'manage-users', component: ManageUsersComponent },
      { path: 'manage-appointments', component: ManageAppointmentsComponent },
      { path: 'manage-beds', component: ManageBedComponent },
      { path: 'manage-doctor', component: ManageDoctorComponent },
      { path: 'admited-patient', component: AdmittedPatientComponent },
      { path: 'admission-patient', component: AdmissionPatientComponent },
      { path: 'bill', component: BillComponent },
      { path: 'discharge', component: DischargePatientComponent },
    ],
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' },
];
