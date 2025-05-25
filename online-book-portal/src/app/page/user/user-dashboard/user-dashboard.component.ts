import { Component } from '@angular/core';
import { UserHeaderComponent } from "../../../section/user-header/user-header.component";
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-user-dashboard',
  imports: [UserHeaderComponent, RouterOutlet],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent {



}
