package co.com.obligacion.servicio.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.obligacion.negocio.interfaz.ObligacionServicio;
import co.com.obligacion.negocio.transferencia.CodigoTO;
import co.com.obligacion.negocio.transferencia.ObligacionTO;

@RestController
@RequestMapping("obligacion")
public class ObligacionRest extends GeneralRest {

	Logger logger = LoggerFactory.getLogger(ObligacionRest.class);

	@Autowired
	ObligacionServicio obligacionServicio;

	@GetMapping
	public List<ObligacionTO> obligaciones() {
		return obligacionServicio.obtenerTodo();
	}

	@PostMapping
	public ResponseEntity<Object> agregar(@Valid @RequestBody ObligacionTO c) {
		logger.info("La obligacion : " + c.toString());
		return new ResponseEntity<>(obligacionServicio.agregar(c), HttpStatus.OK);
	}
	
	@PostMapping(path = "/porCliente")
	public ResponseEntity<Object> obligacionesCliente(@RequestBody CodigoTO codigo) {
		return new ResponseEntity<>(obligacionServicio.obligacionesCliente(codigo.getCodigo()), HttpStatus.OK);
	}

}
