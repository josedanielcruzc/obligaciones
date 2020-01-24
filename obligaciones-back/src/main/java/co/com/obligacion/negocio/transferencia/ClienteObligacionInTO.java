package co.com.obligacion.negocio.transferencia;

import java.io.Serializable;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import co.com.obligacion.negocio.persistencia.entidad.Cliente;


public class ClienteObligacionInTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7415216662753304860L;

	public ClienteObligacionInTO() {
	}

	private String codigo;
	
	@NotBlank(message = "identificacion obligatoria")
	private String identificacion;

	@NotNull(message = "tipoDocumento obligatorio")
	private TipoDocumentoTO tipoDocumento;
	
	private String direccion;

	private String primerApellido;

	private String primerNombre;

	private String razonSocial;

	private String segundoApellido;

	private String segundoNombre;

	private String telefono;
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public TipoDocumentoTO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		builder.append("ClienteObligacionInTO [codigo=");
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
		builder.append("]");
		return builder.toString();
	}

	public Cliente entidad() {
		Cliente c = new Cliente();
		c.setCodigo(this.codigo);
		c.setDireccion(this.direccion);
		c.setRazonSocial(this.razonSocial);
		c.setTelefono(this.telefono);
		c.setPrimerNombre(this.primerNombre);
		c.setSegundoNombre(this.segundoNombre);
		c.setPrimerApellido(this.primerApellido);
		c.setSegundoApellido(this.segundoApellido);
		c.setTipoDocumento(this.tipoDocumento !=null ?  this.tipoDocumento.entidad() : null);
		c.setIdentificacion( this.identificacion );
		return c;
	}
	
	public static ClienteObligacionInTO objectTransfer( Cliente cli ) {
		ClienteObligacionInTO c = new ClienteObligacionInTO();
		c.setCodigo( cli.getCodigo() );
		c.setDireccion(cli.getDireccion());
		c.setRazonSocial(cli.getRazonSocial());
		c.setTelefono(cli.getTelefono());
		c.setPrimerNombre(cli.getPrimerNombre());
		c.setSegundoNombre(cli.getSegundoNombre());
		c.setPrimerApellido(cli.getPrimerApellido());
		c.setSegundoApellido(cli.getSegundoApellido());
		c.setTipoDocumento( cli.getTipoDocumento()!= null ? TipoDocumentoTO.objectTransfer(  cli.getTipoDocumento() ) : null );		
		c.setIdentificacion( cli.getIdentificacion() );
		return c;
	}
	
}
