package co.com.obligacion.negocio.transferencia;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import co.com.obligacion.negocio.persistencia.entidad.Obligacion;

public class ObligacionTO implements Serializable {

	public ObligacionTO() {
	}

	private static final long serialVersionUID = 1L;

	private String codigo;

	@NotBlank(message = "descripcion obligatorio")
	private String descripcion;

	@NotBlank(message = "estado obligatorio")
	private String estado;

	@NotBlank(message = "nombre obligatorio")
	private String nombre;

	@NotBlank(message = "numeroPeriodos obligatorio")
	private String numeroPeriodos;

	@NotBlank(message = "periodoActual obligatorio")
	private String periodoActual;

	@NotBlank(message = "valorPeriodoActual obligatorio")
	private String valorPeriodoActual;

	@NotBlank(message = "valorTotal obligatorio")
	private String valorTotal;

	@NotNull(message = "producto obligatorio")
	private ProductoTO producto;

	@NotNull(message = "cliente obligatorio")
	private ClienteObligacionInTO clienteTO;

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

	public ProductoTO getProducto() {
		return this.producto;
	}

	public void setProducto(ProductoTO producto) {
		this.producto = producto;
	}

	public ClienteObligacionInTO getClienteTO() {
		return this.clienteTO;
	}

	public void setClienteTO(ClienteObligacionInTO clienteTO) {
		this.clienteTO = clienteTO;
	}

	public Obligacion entidad() {
		Obligacion o = new Obligacion();

		o.setCodigo(this.codigo);
		o.setNombre(this.nombre);
		o.setDescripcion(this.descripcion);
		o.setValorTotal(this.valorTotal);
		o.setValorPeriodoActual(this.valorPeriodoActual);
		o.setNumeroPeriodos(this.numeroPeriodos);
		o.setPeriodoActual(periodoActual);
		o.setEstado(this.estado);
		o.setProducto(this.producto != null ? this.producto.entidad() : null);
		o.setCliente(this.clienteTO != null ? this.clienteTO.entidad() : null);
		return o;
	}

	public static ObligacionTO objectTransfer(Obligacion ob) {
		ObligacionTO o = new ObligacionTO();
		o.setCodigo(ob.getCodigo());
		o.setNombre(ob.getNombre());
		o.setDescripcion(ob.getDescripcion());
		o.setValorTotal(ob.getValorTotal());
		o.setValorPeriodoActual(ob.getPeriodoActual());
		o.setNumeroPeriodos(ob.getNumeroPeriodos());
		o.setPeriodoActual(ob.getPeriodoActual());
		o.setClienteTO(ob.getCliente() != null ? ClienteObligacionInTO.objectTransfer(ob.getCliente()) : null);
		o.setEstado(ob.getEstado());
		o.setProducto(ob.getProducto() != null ? ProductoTO.objectTransfer(ob.getProducto()) : null);
		return o;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ObligacionTO [codigo=");
		builder.append(codigo);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", numeroPeriodos=");
		builder.append(numeroPeriodos);
		builder.append(", periodoActual=");
		builder.append(periodoActual);
		builder.append(", valorPeriodoActual=");
		builder.append(valorPeriodoActual);
		builder.append(", valorTotal=");
		builder.append(valorTotal);
		builder.append(", producto=");
		builder.append(producto);
		builder.append(", clienteTO=");
		builder.append(clienteTO);
		builder.append("]");
		return builder.toString();
	}

}