import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { ClienteModel } from '../Modelo/Cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  

  constructor( private http: HttpClient ) { }

  public httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public getInfoCliente(): Promise<ClienteModel[]> {
    return this.getClientesAPI();
  }
 
   private getClientesAPI(): Promise<ClienteModel[]> {
    return this.http.get(`${environment.api_endpoint}/cliente`, this.httpOptions)
      .pipe(
        map((res: any[]) => {
            const listClientes: ClienteModel[] = [];
            res.forEach(cliente => {
              listClientes.push(ClienteModel.fromJson(cliente));
            });
            return listClientes;
          }
        )
      ).toPromise();
  } 

  
  public guardarCliente( clienteModel : ClienteModel ): Promise<ClienteModel> {
    return this.guardarClienteAPI( clienteModel );
  }
 
   private guardarClienteAPI( clienteModel : ClienteModel ): Promise<ClienteModel> {

    const body = clienteModel;

    return this.http.post(`${environment.api_endpoint}/cliente`, body, this.httpOptions)
      .pipe(
        map( (res: any) => {
          console.log('Desde el service: ', res);
          return ClienteModel.fromJson(res);
          }
        )
      ).toPromise();
  } 


}
