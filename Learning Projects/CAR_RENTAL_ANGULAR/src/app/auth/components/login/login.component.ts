import { Component } from '@angular/core';
import { AuthService } from '../../service/auth/auth.service';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {


  loginForm: string = '';
  userId: string = '';

  constructor(private authService: AuthService){}

  // login(){
  //   this.authService.login(this.loginForm).subscribe((res) => {
  //     if(res.userId != null){

  //     }
  //   })

  // }

  

}
