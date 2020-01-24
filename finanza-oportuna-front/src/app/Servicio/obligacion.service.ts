import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { ObligacionModel } from '../Modelo/Obligacion.model';
import { ClienteModel } from '../Modelo/Cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ObligacionService {

  constructor(private http: HttpClient) { }

  public httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public getInfoObligacion(clienteModel: ClienteModel): Promise<ObligacionModel[]> {
    if (clienteModel != null && clienteModel != undefined) {
      return this.getObligacionsClienteAPI( clienteModel );
    } else {
      return this.getObligacionsAPI();
    }
  }

  private getObligacionsAPI(): Promise<ObligacionModel[]> {
    return this.http.get(`${environment.api_endpoint}/obligacion`, this.httpOptions)
      .pipe(
        map((res: any[]) => {
          const listObligacions: ObligacionModel[] = [];
          res.forEach(obligacion => {
            listObligacions.push(ObligacionModel.fromJson(obligacion));
          });
          return listObligacions;
        }
        )
      ).toPromise();
  }

  private getObligacionsClienteAPI(clienteModel: ClienteModel): Promise<ObligacionModel[]> {
    let body = {
      codigo : clienteModel.codigo
    };
    return this.http.post(`${environment.api_endpoint}/obligacion/porCliente`, body, this.httpOptions)
      .pipe(
        map((res: any[]) => {
          const listObligacions: ObligacionModel[] = [];
          res.forEach(obligacion => {
            listObligacions.push(ObligacionModel.fromJson(obligacion));
          });
          return listObligacions;
        }
        )
      ).toPromise();
  }






  public guardarObligacion( obligacionModel : ObligacionModel ): Promise<ClienteModel> {
    return this.guardarObligacionAPI( obligacionModel );
  }
 
   private guardarObligacionAPI( obligacionModel : ObligacionModel ): Promise<ClienteModel> {

    let body = {
      codigo : obligacionModel.codigo,
      descripcion : obligacionModel.descripcion,
      estado: obligacionModel.estado,
      nombre: obligacionModel.nombre,
      numeroPeriodos: obligacionModel.numeroPeriodos,
      periodoActual : obligacionModel.periodoActual,
      producto : {
        codigo : obligacionModel.producto.codigo,
      },
      clienteTO : {
        codigo : obligacionModel.cliente.codigo
      },
      valorPeriodoActual : obligacionModel.valorPeriodoActual,
      valorTotal : obligacionModel.valorTotal,

    };

    

    return this.http.post(`${environment.api_endpoint}/obligacion`, body, this.httpOptions)
      .pipe(
        map( (res: any) => {
          console.log('Desde el service: ', res);
          return ClienteModel.fromJson(res);
          }
        )
      ).toPromise();
  } 




}
