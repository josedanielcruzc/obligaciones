package co.com.obligacion.negocio.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_documento database table.
 * 
 */
@Entity
@Table(name="tipo_documento")
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=6)
	private String codigo;

	@Column(nullable=false, length=3)
	private String prefijo;

	@Column(nullable=false, length=22)
	private String tipo;

//	@OneToMany(mappedBy="tipoDocumento")
//	private List<Cliente> clientes;

	public TipoDocumento() {
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

//	public List<Cliente> getClientes() {
//		return this.clientes;
//	}
//
//	public void setClientes(List<Cliente> clientes) {
//		this.clientes = clientes;
//	}


}