/**
 * 
 */
package co.com.obligacion.negocio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.obligacion.negocio.interfaz.ProductoServicio;
import co.com.obligacion.negocio.persistencia.ProductoRep;
import co.com.obligacion.negocio.persistencia.entidad.Producto;

/**
 * @author Dante
 *
 */
@Service
public class ProductoImpl implements ProductoServicio {

	@Autowired
	ProductoRep productoRep;
	
	@Override
	public List<Producto> obtenerTodo() {
		return productoRep.findAll();
	}

}
