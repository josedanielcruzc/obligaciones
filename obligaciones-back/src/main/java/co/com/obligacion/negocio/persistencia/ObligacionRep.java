/**
 * 
 */
package co.com.obligacion.negocio.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.com.obligacion.negocio.persistencia.entidad.Cliente;
import co.com.obligacion.negocio.persistencia.entidad.Obligacion;

//, PagingAndSortingRepository<Banco, String>

/**
 * @author Dante
 *
 */
@Repository
public interface ObligacionRep extends JpaRepository<Obligacion, String> {
	List<Obligacion> findAll();
	
	List<Obligacion> findByCodigo(String codigo);
	
	@Query("SELECT o FROM Obligacion o WHERE o.cliente = :cliente")
	List<Obligacion> findByCliente(@Param("cliente") Cliente cliente);
	
		
}
