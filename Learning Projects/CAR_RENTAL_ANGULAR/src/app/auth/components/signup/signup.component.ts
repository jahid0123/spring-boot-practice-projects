import { NgIf } from '@angular/common';
import { Component, NgModule, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-signup',
  imports: [RouterModule, FormsModule ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {

  name: string =  '';
  email: string = '';
  password: string = '';
  checkPassword: string = '';

  register() {

    if(this.password !== this.checkPassword){
      alert('password not match');
      return;
    }
    console.log(
      {
       name: this.name, email: this.email,
      password: this.password, checkPassword: this.checkPassword
      }
    );
    

  }
}
