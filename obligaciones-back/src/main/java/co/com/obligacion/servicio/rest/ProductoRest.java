package co.com.obligacion.servicio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.obligacion.negocio.interfaz.ProductoServicio;
import co.com.obligacion.negocio.persistencia.entidad.Producto;

@RestController
@RequestMapping("producto")
public class ProductoRest extends GeneralRest {

	@Autowired
	ProductoServicio productoServicio;
	
	@GetMapping
	public List<Producto> productos() {
		return productoServicio.obtenerTodo() ;
	}	
}
