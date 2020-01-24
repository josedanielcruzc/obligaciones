package co.com.obligacion.negocio.transferencia;

import java.io.Serializable;

import co.com.obligacion.negocio.persistencia.entidad.Banco;

public class BancoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;

	private String nombre;

	public BancoTO() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Banco entidad() {
		Banco o = new Banco();
		o.setCodigo(this.codigo);
		o.setNombre(this.nombre);
		return o;
	}

	public static BancoTO objectTransfer(Banco ob) {
		BancoTO o = new BancoTO();
		o.setCodigo(ob.getCodigo());
		o.setNombre(ob.getNombre());
		return o;
	}

}