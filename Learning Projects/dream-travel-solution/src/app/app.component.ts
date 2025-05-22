import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterComponent } from "./authcomponent/register/register.component";
import { HeaderComponent } from "./section/header/header.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'dream-travel-solution';
}
