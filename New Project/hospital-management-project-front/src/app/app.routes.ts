import { Routes } from '@angular/router';
import { AdmitPatientComponent } from './components/admit-patient/admit-patient.component';
import { DischargePatientComponent } from './components/discharge-patient/discharge-patient.component';
import { GenerateBillComponent } from './components/generate-bill/generate-bill.component';

export const routes: Routes = [
  { path: 'admit', component: AdmitPatientComponent },
  { path: 'discharge', component: DischargePatientComponent },
  { path: 'bill', component: GenerateBillComponent },
  { path: '', redirectTo: 'admit', pathMatch: 'full' }, // default route
];
