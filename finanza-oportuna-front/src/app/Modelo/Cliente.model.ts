import { TipoDocumentoModel } from './TipoDocumento.model';

export class ClienteModel {
  constructor(
    public codigo: string,
    public identificacion: string,
    public tipoDocumento: TipoDocumentoModel,
    public direccion: string,
    public primerApellido: string,
    public primerNombre: string,
    public razonSocial: string,
    public segundoApellido: string,
    public segundoNombre: string,
    public telefono: string
  ) { }

  static fromJson(obj: any) {

    return new ClienteModel(
      obj.codigo,
      obj.identificacion,
      obj.tipoDocumento,
      obj.direccion,
      obj.primerApellido,
      obj.primerNombre,
      obj.razonSocial,
      obj.segundoApellido,
      obj.segundoNombre,
      obj.telefono
    )
  }
}



