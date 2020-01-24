import { BancoModel } from './Banco.model';
import { ObligacionModel } from './Obligacion.model';



export class PagoModel {
  constructor(
    public codigo: string,
    public fechaPago: Date,
    public fechaPagoString : string,
    public periodoPagado: number,
    public valorPagado: number,
    public banco: BancoModel,
    public obligacion: ObligacionModel
    
  ) { }

  static fromJson(obj: any) {

    return new PagoModel(
      obj.codigo,
      obj.fechaPago,
      "",
      obj.periodoPagado,
      obj.valorPagado,
      obj.banco,
      obj.obligacion
    )
  }
}



