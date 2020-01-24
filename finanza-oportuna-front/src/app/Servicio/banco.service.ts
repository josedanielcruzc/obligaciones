import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { BancoModel } from '../Modelo/Banco.model';

@Injectable({
  providedIn: 'root'
})
export class BancoService {

  constructor(private http: HttpClient) { }

  public httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public getBancos(): Promise<BancoModel[]> {
    return this.getBancosAPI();
  }

  private getBancosAPI(): Promise<BancoModel[]> {
    return this.http.get(`${environment.api_endpoint}/banco`, this.httpOptions)
      .pipe(
        map((res: any[]) => {
          const listBanco: BancoModel[] = [];
          res.forEach(banco => {
            listBanco.push(BancoModel.fromJson(banco));
          });
          return listBanco;
        }
        )
      ).toPromise();
  }
}
