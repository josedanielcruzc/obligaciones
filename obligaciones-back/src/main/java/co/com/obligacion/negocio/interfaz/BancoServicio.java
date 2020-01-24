package co.com.obligacion.negocio.interfaz;

import java.util.List;

import co.com.obligacion.negocio.persistencia.entidad.Banco;

public interface BancoServicio {
	
	List<Banco> obtenerTodo();

}
