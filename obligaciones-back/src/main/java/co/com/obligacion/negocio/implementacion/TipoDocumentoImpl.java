/**
 * 
 */
package co.com.obligacion.negocio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.obligacion.negocio.interfaz.TipoDocumentoServicio;
import co.com.obligacion.negocio.persistencia.TipoDocumentoRep;
import co.com.obligacion.negocio.persistencia.entidad.TipoDocumento;

/**
 * @author Dante
 *
 */
@Service
public class TipoDocumentoImpl implements TipoDocumentoServicio{

	@Autowired
	TipoDocumentoRep tipoDocumentoRep;
	
	/**
	 * 
	 */
	public TipoDocumentoImpl() {
	}

	@Override
	public List<TipoDocumento> obtenerTodo() {
		return tipoDocumentoRep.findAll();
	}

}
