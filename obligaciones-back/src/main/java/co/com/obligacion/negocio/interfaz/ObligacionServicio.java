package co.com.obligacion.negocio.interfaz;

import java.util.List;

import co.com.obligacion.negocio.transferencia.ObligacionTO;

public interface ObligacionServicio {

	List<ObligacionTO> obtenerTodo();

	ObligacionTO agregar(ObligacionTO o);
	
	public List<ObligacionTO> obligacionesCliente( String codigo );

}
