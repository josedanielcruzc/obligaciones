package co.com.obligacion.negocio.interfaz;

import java.util.List;

import co.com.obligacion.negocio.persistencia.entidad.Producto;

public interface ProductoServicio {
	
	List<Producto> obtenerTodo();

}
