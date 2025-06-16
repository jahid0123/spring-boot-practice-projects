import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterCompanyComponent } from './auth/signup/register-company/register-company.component';
import { RegisterSeekerComponent } from './auth/signup/register-seeker/register-seeker.component';
import { SeekerDashboardComponent } from './seeker/seeker-dashboard/seeker-dashboard.component';
import { SeekerHomeComponent } from './seeker/seeker-home/seeker-home.component';
import { authGuard } from './core/guard/auth.guard';
import { CompanyDashboardComponent } from './company/company-dashboard/company-dashboard.component';
import { CompanyHomeComponent } from './company/company-home/company-home.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { ManageUsersComponent } from './admin/manage-users/manage-users.component';
import { ManageJobsComponent } from './admin/manage-jobs/manage-jobs.component';
import { ManageApplicationsComponent } from './admin/manage-applications/manage-applications.component';
import { AdminLayoutComponent } from './admin/admin-layout/admin-layout.component';
import { AdminSettingsComponent } from './admin/admin-settings/admin-settings.component';
import { AdminProfileComponent } from './admin/admin-profile/admin-profile.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register-company', component: RegisterCompanyComponent },
  { path: 'register-seeker', component: RegisterSeekerComponent },
  {
    path: 'seeker-dashboard',
    component: SeekerDashboardComponent,
    canActivate: [authGuard],
    data: { roles: ['seeker', 'admin'] },
    children: [
      { path: '', redirectTo: 'seeker-home', pathMatch: 'full' },
      { path: 'seeker-home', component: SeekerHomeComponent },
    ],
  },

  {
    path: 'company-dashboard',
    component: CompanyDashboardComponent,
    canActivate: [authGuard],
    data: { roles: ['company', 'admin'] },
    children: [
      { path: '', redirectTo: 'company-home', pathMatch: 'full' },
      { path: 'company-home', component: CompanyHomeComponent },
    ],
  },

  {
    path: 'admin-layout',
    component: AdminLayoutComponent,
    canActivate: [authGuard],
    data: { roles: ['admin'] },
    children: [
      { path: '', redirectTo: 'admin-dashboard', pathMatch: 'full' },
      { path: 'admin-dashboard', component: AdminDashboardComponent },
      { path: 'admin-home', component: AdminHomeComponent },
      { path: 'manage-users', component: ManageUsersComponent },
      { path: 'manage-jobs', component: ManageJobsComponent },
      { path: 'manage-applications', component: ManageApplicationsComponent },
      { path: 'admin-settings', component: AdminSettingsComponent },
      { path: 'admin-profile', component: AdminProfileComponent },
    ],
  },

  { path: '**', redirectTo: 'login', pathMatch: 'full' },
];
