package co.com.obligacion.servicio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.obligacion.negocio.interfaz.BancoServicio;
import co.com.obligacion.negocio.persistencia.entidad.Banco;

@RestController
@RequestMapping("banco")
public class BancoRest {

	@Autowired
	BancoServicio bancoServicio;
	
	@GetMapping
	public List<Banco> bancos() {
		return bancoServicio.obtenerTodo() ;
	}	
}
