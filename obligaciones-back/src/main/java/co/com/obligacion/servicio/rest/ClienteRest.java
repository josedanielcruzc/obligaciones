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

import co.com.obligacion.negocio.interfaz.ClienteServicio;
import co.com.obligacion.negocio.transferencia.ClienteTO;

@RestController
@RequestMapping("cliente")
public class ClienteRest extends GeneralRest{

	Logger logger = LoggerFactory.getLogger(ClienteRest.class);
	
	@Autowired
	ClienteServicio clienteServicio;
	
	@GetMapping
	public List<ClienteTO> clientes() {
		return clienteServicio.obtenerTodo() ;
	}	
	
	@PostMapping
	public ResponseEntity<Object> agregar( @Valid @RequestBody ClienteTO c ) {
			logger.info( "El cliente : " + c.toString() );
			return new ResponseEntity<>( clienteServicio.agregar(c), HttpStatus.OK ) ;
	}
	
	
}
