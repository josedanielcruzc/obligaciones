package co.com.obligacion.negocio.transferencia;

import java.io.Serializable;

import co.com.obligacion.negocio.persistencia.entidad.Producto;

public class ProductoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;

	private String producto;

	public ProductoTO() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getProducto() {
		return this.producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Producto entidad() {
		Producto o = new Producto();
		o.setCodigo(this.codigo);
		o.setProducto(this.producto);
		return o;
	}

	public static ProductoTO objectTransfer(Producto ob) {
		ProductoTO o = new ProductoTO();
		o.setCodigo(ob.getCodigo());
		o.setProducto(ob.getProducto());
		return o;
	}

}