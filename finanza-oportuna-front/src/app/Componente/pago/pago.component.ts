import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { PagoService } from 'src/app/Servicio/pago.service';
import { PagoModel } from 'src/app/Modelo/Pago.model';
import { ErrorModel } from 'src/app/Modelo/ErrorModel';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { ObligacionModel } from 'src/app/Modelo/Obligacion.model';
import { ObligacionDataservice } from '../../Modelo/ObligacionDataservice';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BancoModel } from 'src/app/Modelo/Banco.model';
import { BancoService } from 'src/app/Servicio/banco.service';

@Component({
  selector: 'app-pago',
  templateUrl: './pago.component.html',
  styleUrls: ['./pago.component.css']
})
export class PagoComponent implements OnInit {

  public obligacionModel: ObligacionModel;
  public listaPagos: PagoModel[] = [];

  public listaBanco: BancoModel[] = [];

  registerForm: FormGroup;
  submitted = false;


  constructor(
    private router: Router,
    private snackbarCtrl: MatSnackBar,
    private pagoCtrl: PagoService,
    private datePipe: DatePipe,
    private obligacionDataservice: ObligacionDataservice,
    private bancoCtrl: BancoService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.obligacionModel = this.obligacionDataservice.obligacionModel;
    console.log("obligacion seleccionada ", this.obligacionModel);
    this.getPagos();
    this.getBancos();
    this.registerForm = this.formBuilder.group({
      banco: ['', Validators.required],
      valorPagado: ['', Validators.required],
      periodoPagado: ['', Validators.required],
    });
  }

  public getPagos() {
    this.pagoCtrl.getInfoPago(this.obligacionModel)
      .then(res => {
        this.listaPagos = res;
        this.listaPagos.forEach(pago => {
          pago.fechaPagoString = this.datePipe.transform(pago.fechaPago, "yyyy/MM/dd h:mm:ss");
        });
        console.log("pagoes :", this.listaPagos);
      }, error => {
        if (error instanceof ErrorModel) {
          this.snackbarCtrl.open(`${error.getMessage()}`, 'Aceptar');
          /* this.router.navigate(['/']); */
        } else if (error.status === 403 || error.status === 500 || error.status === 0) {
          const msg = ErrorModel.getErrorAuthentication(error.status);
          this.snackbarCtrl.open(`${msg}`, 'Aceptar');
        } else {
          this.snackbarCtrl.open(`${error.error.errorMessage}`, 'Aceptar');
        }
      });
  }

  public getBancos() {
    this.bancoCtrl.getBancos().then(res => {
      this.listaBanco = res;
      console.log("lista bancos :", this.listaBanco);
    }, error => {
      if (error instanceof ErrorModel) {
        this.snackbarCtrl.open(`${error.getMessage()}`, 'Aceptar');
        /* this.router.navigate(['/']); */
      } else if (error.status === 403 || error.status === 500 || error.status === 0) {
        const msg = ErrorModel.getErrorAuthentication(error.status);
        this.snackbarCtrl.open(`${msg}`, 'Aceptar');
      } else {
        this.snackbarCtrl.open(`${error.error.errorMessage}`, 'Aceptar');
      }
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  guardarPago(event: any) {
    console.log("guardar");
    this.submitted = true;
    if (this.registerForm.invalid) {
      console.log("guardar false");
      return;
    }
    console.log("guardar ok ", this.registerForm.value);
    const pago = this.registerForm.value;

    console.log("modelo vliente ", this.obligacionDataservice.obligacionModel);
    const pagoModel = new PagoModel(
      null,
      null,
      null,
      pago.periodoPagado,
      pago.valorPagado,
      new BancoModel(
        pago.banco,
        null
      ),
      new ObligacionModel(
        this.obligacionDataservice.obligacionModel.codigo,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
      ),

    )

    this.pagoCtrl.guardarPago(pagoModel).then(res => {
      this.snackbarCtrl.open(`Pago guardado con éxito`, 'Entendido');
    }, error => {
      if (error instanceof ErrorModel) {
        this.snackbarCtrl.open(`${error.getMessage()}`, 'Aceptar');
        this.router.navigate(['/']);
      } else if (error.status === 403 || error.status === 500 || error.status === 0) {
        const msg = ErrorModel.getErrorAuthentication(error.status);
        this.snackbarCtrl.open(`${msg}`, 'Aceptar');
      } else {
        this.snackbarCtrl.open(`${error.error.errorMessage}`, 'Aceptar');
      }
    });
    this.router.navigate(['/']);

  }


}
