/**
 * 
 */
package co.com.obligacion.negocio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.obligacion.negocio.interfaz.ObligacionServicio;
import co.com.obligacion.negocio.persistencia.ClienteRep;
import co.com.obligacion.negocio.persistencia.ObligacionRep;
import co.com.obligacion.negocio.persistencia.entidad.Cliente;
import co.com.obligacion.negocio.persistencia.entidad.Obligacion;
import co.com.obligacion.negocio.transferencia.ObligacionTO;

/**
 * @author Dante
 *
 */
@Service
public class ObligacionImpl implements ObligacionServicio {

	@Autowired
	ObligacionRep obligacionRep;
	
	@Autowired
	ClienteRep clienteRep;

	@Override
	public List<ObligacionTO> obtenerTodo() {
		List<Obligacion> obligaciones = obligacionRep.findAll();
		List<ObligacionTO> obligacionesTO = new ArrayList<>();
		obligaciones.forEach(o -> {
			obligacionesTO.add(ObligacionTO.objectTransfer(o));
		});
		return obligacionesTO;
	}
	
	@Override
	public List<ObligacionTO> obligacionesCliente( String codigo ) {
		Cliente cli = clienteRep.findByCodigo( codigo );;
		List<Obligacion> obligaciones = obligacionRep.findByCliente( cli );
		List<ObligacionTO> obligacionesTO = new ArrayList<>();
		obligaciones.forEach(o -> {
			obligacionesTO.add(ObligacionTO.objectTransfer(o));
		});
		return obligacionesTO;
	}

	public ObligacionTO agregar(ObligacionTO o) {
		Obligacion oDB = obligacionRep.save(o.entidad());
		return ObligacionTO.objectTransfer(oDB);
	}
	

}
