package co.com.obligacion.negocio.transferencia;

import java.io.Serializable;

public class CodigoTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2509732343723473793L;
	private String codigo;

	public CodigoTO() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CodigoTO [codigo=");
		builder.append(codigo);
		builder.append("]");
		return builder.toString();
	}

}
