import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { ProductoModel } from '../Modelo/Producto.model';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private http: HttpClient) { }

  public httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public getProductos(): Promise<ProductoModel[]> {
    return this.getProductoModelAPI();
  }

  private getProductoModelAPI(): Promise<ProductoModel[]> {
    return this.http.get(`${environment.api_endpoint}/producto`, this.httpOptions)
      .pipe(
        map((res: any[]) => {
          const listProducto: ProductoModel[] = [];
          res.forEach(producto => {
            listProducto.push(ProductoModel.fromJson(producto));
          });
          return listProducto;
        }
        )
      ).toPromise();
  }
}
