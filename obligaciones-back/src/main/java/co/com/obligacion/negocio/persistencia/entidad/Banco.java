package co.com.obligacion.negocio.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the banco database table.
 * 
 */
@Entity
@Table(name="banco")
public class Banco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=6)
	private String codigo;

	@Column(nullable=false, length=22)
	private String nombre;

//	@OneToMany(mappedBy="banco")
//	private List<Pago> pagos;

	public Banco() {
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

//	public List<Pago> getPagos() {
//		return this.pagos;
//	}
//
//	public void setPagos(List<Pago> pagos) {
//		this.pagos = pagos;
//	}


}