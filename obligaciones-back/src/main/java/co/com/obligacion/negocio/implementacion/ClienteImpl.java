package co.com.obligacion.negocio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.obligacion.negocio.interfaz.ClienteServicio;
import co.com.obligacion.negocio.persistencia.ClienteRep;
import co.com.obligacion.negocio.persistencia.entidad.Cliente;
import co.com.obligacion.negocio.transferencia.ClienteTO;

/**
 * @author Dante
 *
 */
@Service
public class ClienteImpl implements ClienteServicio {

	@Autowired
	ClienteRep clienteRep;
	
	/**
	 * 
	 */
	public ClienteImpl() {
	}

	@Override
	public List<ClienteTO> obtenerTodo() {
		List<Cliente> clientes= clienteRep.findAll();
		List<ClienteTO> clientesTO = new ArrayList<>(); 
				clientes.forEach( c -> {
			clientesTO.add( ClienteTO.objectTransfer(c) );
		} );
				
		return clientesTO;
	}
	
	public ClienteTO agregar( ClienteTO c ) {
		Cliente cDB = clienteRep.save( c.entidad() ); 
		return ClienteTO.objectTransfer(cDB) ;
	} 

}
