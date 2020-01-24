import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteDataService } from './Modelo/ClienteDataservice';
import { ObligacionDataservice } from './Modelo/ObligacionDataservice';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [
    ClienteDataService, 
    ObligacionDataservice
  ]
})
export class AppComponent {
  title = 'finanza-oportuna-front';

  constructor(private router: Router) {

  }

}
