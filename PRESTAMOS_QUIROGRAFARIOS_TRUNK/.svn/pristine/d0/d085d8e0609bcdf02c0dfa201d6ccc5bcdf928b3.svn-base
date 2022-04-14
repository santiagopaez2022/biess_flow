/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.gov.iess.creditos.dao.IDetalleTransaccionRecaudacionDao;
import ec.gov.iess.creditos.modelo.persistencia.DetalleTransaccionRecaudacion;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author roberto.guizado
 *
 */
@Stateless
public class DetalleTransaccionRecaudacionDaoImpl extends GenericEjbDao<DetalleTransaccionRecaudacion, BigDecimal>
		implements IDetalleTransaccionRecaudacionDao {

	DetalleTransaccionRecaudacionDaoImpl() {
		super(DetalleTransaccionRecaudacion.class);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void guardarDetalleTransaccion(DetalleTransaccionRecaudacion detalleTransaccionRecaudacion) {
		super.insert(detalleTransaccionRecaudacion);
		em.flush();
	}
}
