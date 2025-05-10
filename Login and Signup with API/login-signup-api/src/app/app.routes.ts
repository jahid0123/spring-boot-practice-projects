import { Routes } from '@angular/router';
import { LoginComponent } from './page/auth/login/login.component';
import { LayoutComponent } from './page/other/layout/layout.component';
import { DashboardComponent } from './page/other/layout/dashboard/dashboard.component';

export const routes: Routes = [

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: '',
        component: LayoutComponent,
        children:[
            {
                path: 'dashboard',
                component: DashboardComponent
            }
        ]
    }

];
