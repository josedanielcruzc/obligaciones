export class BancoModel {
  constructor(
    public codigo: string,
    public nombre: string
  ) { }

  static fromJson(obj: any) {

    return new BancoModel(
      obj.codigo,
      obj.nombre
    )
  }
}



