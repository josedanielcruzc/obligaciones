import { ProductoModel } from './Producto.model';
import { ClienteModel } from './Cliente.model';


export class ObligacionModel {
  constructor(
    public codigo: string,
    public descripcion: String,
    public estado: String,
    public nombre: String,
    public numeroPeriodos: number,
    public periodoActual: number,
    public valorPeriodoActual: number,
    public valorTotal: number,
    public producto: ProductoModel,
    public cliente: ClienteModel,
  ) { }

  static fromJson(obj: any) {

    return new ObligacionModel(
      obj.codigo,
      obj.descripcion,
      obj.estado,
      obj.nombre,
      obj.numeroPeriodos,
      obj.periodoActual,
      obj.valorPeriodoActual,
      obj.valorTotal,
      obj.producto,
      obj.clienteTO,
    )
  }
}



