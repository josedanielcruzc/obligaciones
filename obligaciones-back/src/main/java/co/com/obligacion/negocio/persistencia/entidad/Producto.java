package co.com.obligacion.negocio.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="producto")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=6)
	private String codigo;

	@Column(nullable=false, length=100)
	private String producto;

//	@OneToMany(mappedBy="productoBean")
//	private List<Obligacion> obligacions;

	public Producto() {
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

//	public List<Obligacion> getObligacions() {
//		return this.obligacions;
//	}
//
//	public void setObligacions(List<Obligacion> obligacions) {
//		this.obligacions = obligacions;
//	}

}