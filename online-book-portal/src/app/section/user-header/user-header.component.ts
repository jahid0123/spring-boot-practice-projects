import { Component } from '@angular/core';
import { AuthService } from '../../auth/service/auth.service';
import { Router, RouterModule } from '@angular/router';
import { Book } from '../../model/class';

@Component({
  selector: 'app-user-header',
  imports: [RouterModule],
  templateUrl: './user-header.component.html',
  styleUrl: './user-header.component.css'
})
export class UserHeaderComponent {


    constructor(private authService: AuthService, private router: Router) {}

   logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  
}
