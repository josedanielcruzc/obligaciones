package co.com.obligacion.negocio.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.obligacion.manejador.PrefijoSecuenciaIdGenerador;

/**
 * The persistent class for the obligacion database table.
 * 
 */
@Entity
@Table(name = "obligacion")
public class Obligacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OBLIGACION_CODIGO_GENERATOR")
	@GenericGenerator(name = "OBLIGACION_CODIGO_GENERATOR", strategy = "co.com.obligacion.manejador.PrefijoSecuenciaIdGenerador", parameters = {
			@Parameter(name = PrefijoSecuenciaIdGenerador.SEQUENCE_PARAM, value = "sec_obligacion_id"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.PREFIJO_PARAMETRO, value = "OB_"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.FORMATO_PARAMETRO, value = "%03d") })
	private String codigo;

	@Column(nullable = false, length = 200)
	private String descripcion;

	@Column(nullable = false, length = 6)
	private String estado;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(name = "numero_periodos", nullable = false, length = 20)
	private String numeroPeriodos;

	@Column(name = "periodo_actual", nullable = false, length = 20)
	private String periodoActual;

	@Column(name = "valor_periodo_actual", nullable = false, length = 20)
	private String valorPeriodoActual;

	@Column(name = "valor_total", nullable = false, length = 20)
	private String valorTotal;

	@ManyToOne
	@JoinColumn(name = "producto", nullable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "cliente", nullable = false)
	private Cliente cliente;

	public Obligacion() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroPeriodos() {
		return this.numeroPeriodos;
	}

	public void setNumeroPeriodos(String numeroPeriodos) {
		this.numeroPeriodos = numeroPeriodos;
	}

	public String getPeriodoActual() {
		return this.periodoActual;
	}

	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}

	public String getValorPeriodoActual() {
		return this.valorPeriodoActual;
	}

	public void setValorPeriodoActual(String valorPeriodoActual) {
		this.valorPeriodoActual = valorPeriodoActual;
	}

	public String getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}