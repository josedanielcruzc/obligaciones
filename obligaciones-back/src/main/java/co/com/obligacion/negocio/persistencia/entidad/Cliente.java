package co.com.obligacion.negocio.persistencia.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.obligacion.manejador.PrefijoSecuenciaIdGenerador;

/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -863654255528228413L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_CODIGO_GENERATOR")
	@GenericGenerator(name = "CLIENTE_CODIGO_GENERATOR", strategy = "co.com.obligacion.manejador.PrefijoSecuenciaIdGenerador", parameters = {
			@Parameter(name = PrefijoSecuenciaIdGenerador.SEQUENCE_PARAM, value = "sec_cliente_id"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.PREFIJO_PARAMETRO, value = "CL-"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.FORMATO_PARAMETRO, value = "%03d") })
	@Column(name = "codigo", unique = true, nullable = false, length = 6)
	private String codigo;

	@Column(name = "identificacion", unique = true, nullable = false, length = 20)
	private String identificacion;

	@ManyToOne
	@JoinColumn(name = "tipo_documento", nullable = false)
	private TipoDocumento tipoDocumento;

	@Column(length = 20)
	private String direccion;

	@Column(name = "primer_apellido", nullable = false, length = 20)
	private String primerApellido;

	@Column(name = "primer_nombre", nullable = false, length = 20)
	private String primerNombre;

	@Column(name = "razon_social", length = 20)
	private String razonSocial;

	@Column(name = "segundo_apellido", length = 20)
	private String segundoApellido;

	@Column(name = "segundo_nombre", length = 20)
	private String segundoNombre;

	@Column(length = 20)
	private String telefono;

	@OneToMany(mappedBy = "cliente")
	private List<Obligacion> obligacions;

	public Cliente() {
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public List<Obligacion> getObligacions() {
		return this.obligacions;
	}

	public void setObligacions(List<Obligacion> obligacions) {
		this.obligacions = obligacions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [codigo=");
		builder.append(codigo);
		builder.append(", identificacion=");
		builder.append(identificacion);
		builder.append(", tipoDocumento=");
		builder.append(tipoDocumento);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append(", primerApellido=");
		builder.append(primerApellido);
		builder.append(", primerNombre=");
		builder.append(primerNombre);
		builder.append(", razonSocial=");
		builder.append(razonSocial);
		builder.append(", segundoApellido=");
		builder.append(segundoApellido);
		builder.append(", segundoNombre=");
		builder.append(segundoNombre);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", obligacions=");
		builder.append(obligacions);
		builder.append("]");
		return builder.toString();
	}

}