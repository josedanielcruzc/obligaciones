package co.com.obligacion.negocio.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.obligacion.manejador.PrefijoSecuenciaIdGenerador;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the pago database table.
 * 
 */
@Entity
@Table(name = "pago")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGO_CODIGO_GENERATOR")
	@GenericGenerator(name = "PAGO_CODIGO_GENERATOR", strategy = "co.com.obligacion.manejador.PrefijoSecuenciaIdGenerador", parameters = {
			@Parameter(name = PrefijoSecuenciaIdGenerador.SEQUENCE_PARAM, value = "sec_pago_id"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.PREFIJO_PARAMETRO, value = "PA_"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.FORMATO_PARAMETRO, value = "%03d") })
	private String codigo;

	@Column(name = "fecha_pago", nullable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;

	@Column(name = "periodo_pagado", nullable = false)
	private Integer periodoPagado;

	@Column(name = "valor_pagado", nullable = false, precision = 5, scale = 2)
	private BigDecimal valorPagado;

	@ManyToOne
	@JoinColumn(name = "banco_pago", nullable = false)
	private Banco banco;

	@ManyToOne
	@JoinColumn(name = "obligacion", nullable = false )
	private Obligacion obligacionBean;

	public Pago() {
	}

	@PrePersist
	protected void onCreate() {
		fechaPago = new Date();
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

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Obligacion getObligacionBean() {
		return this.obligacionBean;
	}

	public void setObligacionBean(Obligacion obligacionBean) {
		this.obligacionBean = obligacionBean;
	}

}