/**
 * 
 */
package co.com.obligacion.negocio.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.com.obligacion.negocio.persistencia.entidad.Obligacion;
import co.com.obligacion.negocio.persistencia.entidad.Pago;

//, PagingAndSortingRepository<Banco, String>

/**
 * @author Dante
 *
 */
@Repository
public interface PagoRep extends JpaRepository<Pago, String> {
	List<Pago> findAll();

	List<Pago> findByObligacionBean(Obligacion obligacion);
	
}
