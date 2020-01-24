/**
 * 
 */
package co.com.obligacion.negocio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.obligacion.negocio.interfaz.BancoServicio;
import co.com.obligacion.negocio.persistencia.BancoRep;
import co.com.obligacion.negocio.persistencia.entidad.Banco;

/**
 * @author Dante
 *
 */
@Service
public class BancoImpl implements BancoServicio {

	@Autowired
	BancoRep bancoRep;
	
	@Override
	public List<Banco> obtenerTodo() {
		return bancoRep.findAll();
	}

}
