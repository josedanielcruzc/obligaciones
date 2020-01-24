
export class TipoDocumentoModel {
  constructor(
    public codigo: string,
    public prefijo: string,
    public tipo: string
  ) { }

  static fromJson(obj: any) {

    return new TipoDocumentoModel(
      obj.codigo,
      obj.prefijo,
      obj.tipo
    )
  }
}



