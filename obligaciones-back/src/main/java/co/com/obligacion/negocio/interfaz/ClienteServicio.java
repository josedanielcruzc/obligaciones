package co.com.obligacion.negocio.interfaz;

import java.util.List;

import co.com.obligacion.negocio.transferencia.ClienteTO;

public interface ClienteServicio {
	
	List<ClienteTO> obtenerTodo();
	
	ClienteTO agregar( ClienteTO c );

}
