import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteComponent } from './Componente/cliente/cliente.component';
import { ObligacionComponent } from './Componente/obligacion/obligacion.component';
import { PagoComponent } from './Componente/pago/pago.component';


const routes: Routes = [
  { path: '', redirectTo: 'cliente', pathMatch: 'full' },
  {path : 'cliente', component : ClienteComponent},
  {path : 'obligacion', component : ObligacionComponent},
  {path : 'pago', component : PagoComponent},
  /* { path: '**', component: ClienteComponent }  */// If no matching route found, go back to home route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
