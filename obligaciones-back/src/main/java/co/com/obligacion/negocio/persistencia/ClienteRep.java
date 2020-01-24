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

//, PagingAndSortingRepository<Banco, String>

/**
 * @author Dante
 *
 */
@Repository
public interface ClienteRep extends JpaRepository<Cliente, String> {
	List<Cliente> findAll();
	
	@Query("SELECT c FROM Cliente c WHERE c.codigo = :codigo")
	Cliente findByCodigo(@Param("codigo") String codigo);
	;
	
	
}
