package co.com.obligacion.servicio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.obligacion.negocio.interfaz.TipoDocumentoServicio;
import co.com.obligacion.negocio.persistencia.entidad.TipoDocumento;

@RestController
@RequestMapping("tipoDocumento")
public class TipoDocumentoRest {

	@Autowired
	TipoDocumentoServicio tipoDocumentoServicio;
	
	@GetMapping
	public List<TipoDocumento> tipoDocumentos() {
		return tipoDocumentoServicio.obtenerTodo() ;
	}
	
}
