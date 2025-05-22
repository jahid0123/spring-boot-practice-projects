import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../authcomponent/service/auth.service';

@Component({
  selector: 'app-header',
  imports: [RouterModule, NgIf, NgClass],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  user = {
    name: '',
    avatarUrl: 'https://cdn-icons-png.flaticon.com/512/3135/3135715.png'
  };

  role: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    
      this.role = localStorage.getItem('role');
    

     const name = localStorage.getItem('username');
    const avatar = localStorage.getItem('avatarUrl');

    if (name) this.user.name = name;
    if (avatar) this.user.avatarUrl = avatar;
  }

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
