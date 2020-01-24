package co.com.obligacion.negocio.transferencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import co.com.obligacion.negocio.persistencia.entidad.Pago;

public class PagoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;

	private Date fechaPago;

	@Min(message = "periodoPagado obligatorio", value = 1)
	private Integer periodoPagado;

	@Min(message = "valorPagado obligatorio", value = 1)
	private BigDecimal valorPagado;

	@NotNull(message = "banco obligatorio")
	private BancoTO banco;

	@NotNull(message = "obligacion obligatorio")
	private ObligacionTO obligacion;

	public PagoTO() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getPeriodoPagado() {
		return this.periodoPagado;
	}

	public void setPeriodoPagado(Integer periodoPagado) {
		this.periodoPagado = periodoPagado;
	}

	public BigDecimal getValorPagado() {
		return this.valorPagado;
	}

	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}

	public BancoTO getBanco() {
		return this.banco;
	}

	public void setBanco(BancoTO banco) {
		this.banco = banco;
	}

	public ObligacionTO getObligacion() {
		return this.obligacion;
	}

	public void setObligacion(ObligacionTO obligacion) {
		this.obligacion = obligacion;
	}

	public Pago entidad() {
		Pago o = new Pago();
		o.setCodigo(this.codigo);
		o.setFechaPago(this.fechaPago);
		o.setValorPagado(this.valorPagado);
		o.setPeriodoPagado(this.periodoPagado);
		o.setBanco(this.banco.entidad());
		o.setObligacionBean(this.obligacion.entidad());

		return o;
	}

	public static PagoTO objectTransfer(Pago ob) {
		PagoTO o = new PagoTO();
		o.setCodigo(ob.getCodigo());
		o.setFechaPago(ob.getFechaPago());
		o.setValorPagado(ob.getValorPagado());
		o.setPeriodoPagado(ob.getPeriodoPagado());
		o.setBanco(BancoTO.objectTransfer(ob.getBanco()));
		o.setObligacion(ObligacionTO.objectTransfer(ob.getObligacionBean()));
		return o;
	}

}