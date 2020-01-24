import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';

import { AppComponent } from './app.component';
import { ClienteComponent } from './Componente/cliente/cliente.component';
import { ObligacionComponent } from './Componente/obligacion/obligacion.component';
import { PagoComponent } from './Componente/pago/pago.component';

import { ClienteService } from './Servicio/cliente.service';
import { ObligacionService } from './Servicio/obligacion.service';
import { PagoService } from './Servicio/pago.service';




@NgModule({
  declarations: [
    AppComponent,
    ClienteComponent,
    ObligacionComponent,
    PagoComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSnackBarModule
  ],
  providers: [
    DatePipe,
    ClienteService,
    ObligacionService,
    PagoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
