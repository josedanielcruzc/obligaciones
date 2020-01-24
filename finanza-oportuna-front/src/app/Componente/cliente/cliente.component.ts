import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { ClienteService } from 'src/app/Servicio/cliente.service';
import { ClienteModel } from 'src/app/Modelo/Cliente.model';
import { TipoDocumentoService } from 'src/app/Servicio/tipo-documento.service';
import { TipoDocumentoModel } from 'src/app/Modelo/TipoDocumento.model';
import { ErrorModel } from 'src/app/Modelo/ErrorModel';
import { Router } from '@angular/router';
import { ClienteDataService } from '../../Modelo/ClienteDataservice';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  public listaClientes: ClienteModel[] = [];
  public listaTipoDocumento: TipoDocumentoModel[] = [];

  registerForm: FormGroup;
  submitted = false;

  public mostrarTarjetaGuardar = false;
  public mostrarCamposNit = false;

  constructor(
    private router: Router,
    private snackbarCtrl: MatSnackBar,
    private clienteCtrl: ClienteService,
    private clienteDataService: ClienteDataService,
    private tipoDocumentoCtrl: TipoDocumentoService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.clienteDataService.clienteModel = null;
    this.getClientes();
    this.gettiposDocumento();

    this.registerForm = this.formBuilder.group({
      tipoDocumento: ['', Validators.required],
      documento: ['', Validators.required],
      primerNombre: ['', Validators.required],
      primerApellido: ['', Validators.required],
      segundoNombre: ['', null],
      segundoApellido: ['', null],
      direccion: ['', null],
      telefono: ['', null],
      razonsoc: ['', null],
    });
  }

  public cambioTipoDocumento(newValue) {
    console.log('nuevo valor ', newValue);
    this.renderizarControles(newValue);
  }

  public renderizarControles(valor) {
    if (valor == "TD-004") {
      this.registerForm.get('primerNombre').clearValidators();
      this.registerForm.get('primerNombre').updateValueAndValidity();
      this.registerForm.get('primerApellido').clearValidators();
      this.registerForm.get('primerApellido').updateValueAndValidity();
      this.registerForm.get('razonsoc').setValidators(Validators.required);
      this.registerForm.get('razonsoc').updateValueAndValidity();
      this.mostrarCamposNit = true;
    } else {
      this.registerForm.get('primerNombre').setValidators(Validators.required);
      this.registerForm.get('primerNombre').updateValueAndValidity();
      this.registerForm.get('primerApellido').setValidators(Validators.required);
      this.registerForm.get('primerApellido').updateValueAndValidity();
      this.registerForm.get('razonsoc').clearValidators();
      this.registerForm.get('razonsoc').updateValueAndValidity();
      this.mostrarCamposNit = false;
    }
  }

  public getClientes() {
    this.clienteCtrl.getInfoCliente()
      .then(res => {
        this.listaClientes = res;
        console.log("clientes :", this.listaClientes);
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


  public gettiposDocumento() {
    this.tipoDocumentoCtrl.getTipoDocumentos().then(res => {
      this.listaTipoDocumento = res;
      console.log("listaTipoDocumento :", this.listaClientes);
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

  public clienteSeleccionado(event: any, cliente: any, accion: string) {
    this.clienteDataService.clienteModel = cliente;

    if (accion === 'editar') {
      this.registerForm.setValue({
        tipoDocumento: cliente.tipoDocumento.codigo,
        documento: cliente.identificacion,
        primerNombre: cliente.primerNombre,
        primerApellido: cliente.primerApellido,
        segundoNombre: cliente.segundoNombre,
        segundoApellido: cliente.segundoApellido,
        direccion: cliente.direccion,
        telefono: cliente.telefono,
        razonsoc: cliente.razonSocial
      }
      );
      this.renderizarControles(cliente.tipoDocumento.codigo);

      this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
    } else if (accion === 'obligaciones') {
      this.router.navigate(['obligacion']);
    }

  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  cancelarGuardado() {
    this.clienteDataService.clienteModel = null;
    this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
    this.resetFormulario();
  }

  agregarCliente() {
    this.renderizarControles('');
    this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
  }

  guardarCliente(event: any) {
    this.submitted = true;
    if (this.registerForm.invalid) {
      console.log('fallo');
      return;
    }
    const cliente = this.registerForm.value;
    const clienteModel = new ClienteModel(
      this.clienteDataService.clienteModel != null ? this.clienteDataService.clienteModel.codigo : null,
      cliente.documento,
      new TipoDocumentoModel(
        cliente.tipoDocumento,
        null,
        null
      ),
      cliente.direccion,
      cliente.tipoDocumento == "TD-004" ? cliente.razonsoc : cliente.primerApellido,
      cliente.tipoDocumento == "TD-004" ? cliente.razonsoc : cliente.primerNombre,
      cliente.razonsoc,
      cliente.segundoApellido,
      cliente.segundoNombre,
      cliente.telefono
    )
    console.log("Modelo G", clienteModel);
    this.clienteCtrl.guardarCliente(clienteModel).then(res => {
      this.clienteDataService.clienteModel = null;
      this.mostrarTarjetaGuardar = !this.mostrarTarjetaGuardar;
      this.snackbarCtrl.open(`Cliente guardado con éxito`, 'Entendido')
        .onAction().subscribe(() => {
          this.resetFormulario();
          this.getClientes();
        });
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


  public resetFormulario() {

    this.registerForm.get("tipoDocumento").setValue("");
    this.registerForm.get("tipoDocumento").setValue("");
    this.registerForm.get("documento").setValue("");
    this.registerForm.get("primerNombre").setValue("");
    this.registerForm.get("primerApellido").setValue("");
    this.registerForm.get("segundoNombre").setValue("");
    this.registerForm.get("segundoApellido").setValue("");
    this.registerForm.get("direccion").setValue("");
    this.registerForm.get("telefono").setValue("");
    this.registerForm.get("razonsoc").setValue("");
  }

}

