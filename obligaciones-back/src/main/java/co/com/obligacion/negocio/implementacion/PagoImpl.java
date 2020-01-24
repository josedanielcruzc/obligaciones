/**
 * 
 */
package co.com.obligacion.negocio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.obligacion.negocio.interfaz.PagoServicio;
import co.com.obligacion.negocio.persistencia.BancoRep;
import co.com.obligacion.negocio.persistencia.ObligacionRep;
import co.com.obligacion.negocio.persistencia.PagoRep;
import co.com.obligacion.negocio.persistencia.entidad.Obligacion;
import co.com.obligacion.negocio.persistencia.entidad.Pago;
import co.com.obligacion.negocio.transferencia.PagoTO;

/**
 * @author Dante
 *
 */
@Service
public class PagoImpl implements PagoServicio {

	@Autowired
	PagoRep pagoRep;
	@Autowired
	BancoRep bancoRep;
	@Autowired
	ObligacionRep obligacionRep;

	@Override
	public List<PagoTO> obtenerTodo() {
		List<Pago> pagos = pagoRep.findAll();
		List<PagoTO> pagosTO = new ArrayList<>();
		pagos.forEach(o -> {
			pagosTO.add(PagoTO.objectTransfer(o));
		});
		return pagosTO;
	}

	@Override
	public List<PagoTO> pagosObligacion( String codigo ) {
		Obligacion obli = new Obligacion();
		obli.setCodigo(codigo);
		List<Pago> pagos = pagoRep.findByObligacionBean(obli );
		List<PagoTO> pagosTO = new ArrayList<>();
		pagos.forEach(o -> {
			pagosTO.add(PagoTO.objectTransfer(o));
		});
		return pagosTO;
	}
	
	@Override
	@Transactional
	public PagoTO agregar(PagoTO p) {
		Pago pago = null;
		Obligacion obligacion = obligacionRep.findByCodigo(p.getObligacion().getCodigo()).get(0);
		Integer valorTotal = Integer.parseInt(obligacion.getValorTotal());
		Integer valorPeriodoActual = Integer.parseInt(obligacion.getValorPeriodoActual());
		Integer valorPagado = p.getValorPagado().intValue();
		if (valorPagado <= valorTotal) {
			if (valorPagado >= valorPeriodoActual) {
				while (valorPagado > 0) {
					valorPagado = valorPagado - valorPeriodoActual;
					valorTotal = valorTotal - valorPeriodoActual;
					Integer periodoActual = Integer.parseInt(obligacion.getPeriodoActual());
					Integer numeroPeriodos = Integer.parseInt(obligacion.getNumeroPeriodos());
					obligacion.setPeriodoActual("" + (periodoActual + 1));
					obligacion.setNumeroPeriodos("" + (numeroPeriodos - 1));
				}
				obligacion.setValorTotal("" + valorTotal);
				if (valorTotal < 0) {
					obligacion.setEstado("PAGADO");
				}
			}
			obligacionRep.save(obligacion);
			pago = pagoRep.save(p.entidad());
		}
		return PagoTO.objectTransfer(pago);
	}

}
