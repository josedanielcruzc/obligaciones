package co.com.obligacion.negocio.transferencia;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import co.com.obligacion.negocio.persistencia.entidad.TipoDocumento;

public class TipoDocumentoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1845431253277038645L;

	@NotBlank(message = "codigo obligatorio")
	private String codigo;

	private String prefijo;

	private String tipo;

	public TipoDocumentoTO() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPrefijo() {
		return this.prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public TipoDocumento entidad() {
		TipoDocumento t = new TipoDocumento();
		t.setCodigo(this.codigo);
		t.setPrefijo(this.prefijo);
		t.setTipo(this.tipo);
		return t;
	}
	
	public static TipoDocumentoTO objectTransfer( TipoDocumento td ) {
		TipoDocumentoTO d = new TipoDocumentoTO();
		d.setCodigo(td.getCodigo());
		d.setPrefijo(td.getPrefijo());
		d.setTipo(td.getTipo());
		return d;
	}
	
}