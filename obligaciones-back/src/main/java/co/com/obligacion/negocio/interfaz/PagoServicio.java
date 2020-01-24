package co.com.obligacion.negocio.interfaz;

import java.util.List;

import co.com.obligacion.negocio.transferencia.PagoTO;

public interface PagoServicio {
	
	List<PagoTO> obtenerTodo();

	PagoTO agregar(PagoTO o);
	
	public List<PagoTO> pagosObligacion( String codigo );
}
