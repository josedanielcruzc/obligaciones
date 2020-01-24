export class ProductoModel {
  constructor(
    public codigo: string,
    public producto: string
  ) { }

  static fromJson(obj: any) {

    return new ProductoModel(
      obj.codigo,
      obj.producto
    )
  }
}



