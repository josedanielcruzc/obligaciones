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

import co.com.obligacion.negocio.interfaz.PagoServicio;
import co.com.obligacion.negocio.transferencia.CodigoTO;
import co.com.obligacion.negocio.transferencia.PagoTO;

@RestController
@RequestMapping("pago")
public class PagoRest extends GeneralRest {

	Logger logger = LoggerFactory.getLogger(PagoRest.class);

	@Autowired
	PagoServicio pagoServicio;

	@GetMapping
	public List<PagoTO> pagos() {
		return pagoServicio.obtenerTodo();
	}

	@PostMapping
	public ResponseEntity<Object> agregar(@Valid @RequestBody PagoTO c) {
		logger.info("El PagoTO : " + c.toString());
		return new ResponseEntity<>(pagoServicio.agregar(c), HttpStatus.OK);
	}

	@PostMapping(path = "/porObligacion")
	public ResponseEntity<Object> pagosObligacion(@RequestBody CodigoTO codigo) {
		return new ResponseEntity<>(pagoServicio.pagosObligacion(codigo.getCodigo()), HttpStatus.OK);
	}

}
