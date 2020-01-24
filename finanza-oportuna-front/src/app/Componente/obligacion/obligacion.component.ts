import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { ObligacionService } from 'src/app/Servicio/obligacion.service';
import { ObligacionModel } from 'src/app/Modelo/Obligacion.model';
import { ErrorModel } from 'src/app/Modelo/ErrorModel';
import { Router } from '@angular/router';
import { ClienteModel } from 'src/app/Modelo/Cliente.model';
import { ClienteDataService } from '../../Modelo/ClienteDataservice';
import { ObligacionDataservice } from '../../Modelo/ObligacionDataservice';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductoModel } from 'src/app/Modelo/Producto.model';
import { ProductoService } from 'src/app/Servicio/producto.service';

@Component({
  selector: 'app-obligacion',
  templateUrl: './obligacion.component.html',
  styleUrls: ['./obligacion.component.css']
})
export class ObligacionComponent implements OnInit {

  public clienteModel: ClienteModel;
  public listaObligacions: ObligacionModel[] = [];

  public listaProductoModel: ProductoModel[] = [];

  registerForm: FormGroup;
  submitted = false;

  public mostrarTarjetaGuardar = false;

  constructor(
    private router: Router,
    private snackbarCtrl: MatSnackBar,
    private obligacionCtrl: ObligacionService,
    private clienteDataService: ClienteDataService,
    private obligacionDataservice: ObligacionDataservice,
    private productoCtrl: ProductoService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.obligacionDataservice.obligacionModel = null;
    this.clienteModel = this.clienteDataService.clienteModel;

    this.getObligaciones();
    this.getProductos();
    this.registerForm = this.formBuilder.group({
      descripcion: ['', Validators.required],
      estado: ['', null],
      nombre: ['', Validators.required],
      numeroPeriodos: ['', Validators.required],
      periodoActual: ['', Validators.required],
      valorPeriodoActual: ['', Validators.required],
      valorTotal: ['', Validators.required],
      producto: ['', Validators.required]
    });

  }//init

  public getProductos() {
    this.productoCtrl.getProductos().then(res => {
      this.listaProductoModel = res;
      console.log("listaTipoDocumento :", this.listaProductoModel);
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

  public getObligaciones() {
    this.obligacionCtrl.getInfoObligacion(this.clienteModel)
      .then(res => {
        this.listaObligacions = res;
        console.log("obligaciones :", this.listaObligacions);
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


  public obligacionSeleccionada(event: any, obligacion: any, accion: string) {
    this.obligacionDataservice.obligacionModel = obligacion;
    if (accion === 'editar') {

      this.registerForm.setValue({
        descripcion: obligacion.descripcion,
        estado: obligacion.estado,
        nombre: obligacion.nombre,
        numeroPeriodos: obligacion.numeroPeriodos,
        periodoActual: obligacion.periodoActual,
        valorPeriodoActual: obligacion.valorPeriodoActual,
        valorTotal: obligacion.valorTotal,
        producto: obligacion.producto.codigo
      });

      this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
    } else if (accion === 'pagos') {
      this.router.navigate(['pago']);
    }

  }



  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  cancelarGuardado() {
    this.obligacionDataservice.obligacionModel = null;
    this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
  }

  agregarObligacion() {
    this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
  }


  guardarObligacion(event: any) {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    const obligacion = this.registerForm.value;
    console.log("modelo : ", this.obligacionDataservice.obligacionModel);
    console.log("modelo 2: ", this.clienteDataService.clienteModel.codigo);
    const obligacionModel = new ObligacionModel(
      this.obligacionDataservice.obligacionModel != null ? this.obligacionDataservice.obligacionModel.codigo : null,
      obligacion.descripcion,
      obligacion.estado == true ? 'PNDNTE' : 'PAGADO',
      obligacion.nombre,
      obligacion.numeroPeriodos,
      obligacion.periodoActual,
      obligacion.valorPeriodoActual,
      obligacion.valorTotal,
      new ProductoModel(
        obligacion.producto,
        null
      ),
      new ClienteModel(
        this.clienteDataService.clienteModel.codigo,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
      )

    )
    console.log("Modelo G", obligacionModel);
    this.obligacionCtrl.guardarObligacion(obligacionModel).then(res => {
      this.obligacionDataservice.obligacionModel = null;
      this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
      this.snackbarCtrl.open(`Obligacion guardada con éxito`, 'Entendido')
        .onAction().subscribe(() => { this.getObligaciones() }); 
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
  }
}
