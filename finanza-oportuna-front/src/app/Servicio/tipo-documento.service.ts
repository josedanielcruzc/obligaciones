import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { TipoDocumentoModel } from '../Modelo/TipoDocumento.model';

@Injectable({
  providedIn: 'root'
})
export class TipoDocumentoService {

  constructor(private http: HttpClient) { }

  public httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public getTipoDocumentos(): Promise<TipoDocumentoModel[]> {
    return this.getTipoDocumentosAPI();
  }

  private getTipoDocumentosAPI(): Promise<TipoDocumentoModel[]> {
    return this.http.get(`${environment.api_endpoint}/tipoDocumento`, this.httpOptions)
      .pipe(
        map((res: any[]) => {
          const listTipoDocumento: TipoDocumentoModel[] = [];
          res.forEach(tipoDocumento => {
            listTipoDocumento.push(TipoDocumentoModel.fromJson(tipoDocumento));
          });
          return listTipoDocumento;
        }
        )
      ).toPromise();
  }

}
