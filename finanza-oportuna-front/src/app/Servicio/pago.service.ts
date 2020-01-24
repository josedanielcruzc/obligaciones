import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { PagoModel } from '../Modelo/Pago.model';
import { ObligacionModel } from '../Modelo/Obligacion.model';

@Injectable({
  providedIn: 'root'
})
export class PagoService {

 
  constructor( private http: HttpClient ) { }

  public httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public getInfoPago(  obligacionModel : ObligacionModel ): Promise<PagoModel[]> {
    if( obligacionModel != null && obligacionModel != undefined ){
      return this.getPagosObligacionAPI( obligacionModel );
    }else{
      return this.getPagosAPI();
    }
  }
 
  public getPagosAPI( ): Promise<PagoModel[]> {
    return this.http.get(`${environment.api_endpoint}/pago`, this.httpOptions)
      .pipe(
        map((res: any[]) => {
            const listPagos: PagoModel[] = [];
            res.forEach(pago => {
              listPagos.push(PagoModel.fromJson(pago));
            });
            return listPagos;
          }
        )
      ).toPromise();
  } 

  public getPagosObligacionAPI( obligacionModel : ObligacionModel ): Promise<PagoModel[]> {
    let body = {
      codigo : obligacionModel.codigo
    };
    return this.http.post(`${environment.api_endpoint}/pago/porObligacion`, body, this.httpOptions)
      .pipe(
        map((res: any[]) => {
            const listPagos: PagoModel[] = [];
            res.forEach(pago => {
              listPagos.push(PagoModel.fromJson(pago));
            });
            return listPagos;
          }
        )
      ).toPromise();
  } 






  public guardarPago( pagoModel : PagoModel ): Promise<PagoModel> {
    return this.guardarPagoAPI( pagoModel );
  }
 
   private guardarPagoAPI( pagoModel : PagoModel ): Promise<PagoModel> {

    const body = pagoModel;

    return this.http.post(`${environment.api_endpoint}/pago`, body, this.httpOptions)
      .pipe(
        map( (res: any) => {
          console.log('Desde el service: ', res);
          return PagoModel.fromJson(res);
          }
        )
      ).toPromise();
  } 




}
