/**
 * 
 */
package co.com.obligacion.negocio.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.com.obligacion.negocio.persistencia.entidad.Banco;

//, PagingAndSortingRepository<Banco, String>

/**
 * @author Dante
 *
 */
@Repository
public interface BancoRep extends JpaRepository<Banco, String> {
	List<Banco> findAll();
	
	List<Banco> findByCodigo(String codigo);
		
}
