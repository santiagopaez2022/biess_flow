/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ITransaccionRecaudacionDao;
import ec.gov.iess.creditos.modelo.persistencia.TransaccionRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.TransaccionRecaudacionPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author roberto.guizado
 *
 */
@Stateless
public class TransaccionRecaudacionDaoImpl extends GenericEjbDao<TransaccionRecaudacion, TransaccionRecaudacionPk>
		implements ITransaccionRecaudacionDao {

	private static final String CEDULA_APPEND = ", cedula: ";
	private static final String MSG_INF_NUT = "No se encontro informacion con los datos, nut:";
	private static final String CEDULA = "cedula";
	LoggerBiess log = LoggerBiess.getLogger(TransaccionRecaudacionDaoImpl.class);

	TransaccionRecaudacionDaoImpl() {
		super(TransaccionRecaudacion.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.ITransaccionRecaudacionDao#guardarTransaccion(ec.gov
	 * .iess.creditos.modelo.persistencia. TransaccionRecaudacion)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void guardarTransaccion(TransaccionRecaudacion transaccionRecaudacion) {
		super.insert(transaccionRecaudacion);
		em.flush();
	}

	@Override
	public long obtenerValorSecuencial(String nombreSecuencia) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append(nombreSecuencia);
		sql.append(".nextval from dual");
		Query query = em.createNativeQuery(sql.toString());
		BigDecimal resultado = (BigDecimal) query.getSingleResult();
		return resultado.longValue();
	}

	@Override
	public TransaccionRecaudacion obtenerTransaccion(BigDecimal nut, String cedula) {
		Query query = em.createNamedQuery("TransaccionRecaudacion.obtenerTransaccionCedulaNut");
		query.setParameter("nut", nut);
		query.setParameter(CEDULA, cedula);
		TransaccionRecaudacion transaccionRecaudacion;
		try {
			transaccionRecaudacion = (TransaccionRecaudacion) query.getSingleResult();
		} catch (NoResultException e) {
			StringBuilder mensaje = new StringBuilder(MSG_INF_NUT)
					.append(nut).append(CEDULA_APPEND).append(cedula);
			log.error(mensaje);
			transaccionRecaudacion = null;
		}
		return transaccionRecaudacion;
	}

	@Override
	public boolean obtenerTransaccionEpl(BigDecimal nut, String cedula) {
		Query query = em.createNamedQuery("TransaccionRecaudacion.obtenerTransaccionCedulaNutEpl");
		query.setParameter("nut", nut);
		query.setParameter(CEDULA, cedula);
		boolean tieneDividendosEpl=Boolean.FALSE;
          List<TransaccionRecaudacion>	transacciones =  query.getResultList();
		   
		   if(transacciones!=null && transacciones.size()>0) {
			   //tomo la primera transaccion en EPL
			   tieneDividendosEpl=Boolean.TRUE;
		   }
		return tieneDividendosEpl;
	}
	
	
	

	@Override
	public TransaccionRecaudacion obtenerTransaccion(TransaccionRecaudacion transaccionRecaudacion) {
		Query query = em.createNamedQuery("TransaccionRecaudacion.consultarTransAjustePlanilla");
		query.setParameter("nut", transaccionRecaudacion.getTrNut());
		query.setParameter(CEDULA, transaccionRecaudacion.getCedula());
		query.setParameter("codigoSucursal", transaccionRecaudacion.getCodigoSucursal());
		query.setParameter("rucEmpresa", transaccionRecaudacion.getRucEmpresa());
		query.setParameter("anioPeriodo", transaccionRecaudacion.getAnioPeriodo());
		query.setParameter("mesPeriodo", transaccionRecaudacion.getMesPeriodo());
		query.setParameter("secuencialDetallePlanilla", transaccionRecaudacion.getSecuencialDetallePlanilla());
		query.setParameter("ttIdtipotransaccion", transaccionRecaudacion.getPk().getTrIdtipotransaccion());
		TransaccionRecaudacion transaccion;
		try {
			transaccion = (TransaccionRecaudacion) query.getSingleResult();
		} catch (NoResultException e) {
			StringBuilder mensaje = new StringBuilder(MSG_INF_NUT)
					.append(transaccionRecaudacion.getTrNut()).append(CEDULA_APPEND).append(transaccionRecaudacion.getCedula());
			log.error(mensaje);
			transaccion = null;
		}
		return transaccion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.ph.dao.TransaccionRecaudacionDao#
	 * actualizarEstadoAjustePlanilla(java.lang.String, java.util.Date,
	 * java.math.BigDecimal, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void actualizarEstadoAjustePlanilla(String estado, Date fechaProceso, BigDecimal secuencia,
			Long idTipoTransaccion, Long idTransaccion) {
		Query query = em.createNamedQuery("TransaccionRecaudacion.actualizarEstadoAjustePlanilla");
		query.setParameter("estado", estado);
		query.setParameter("fechaProceso", fechaProceso);
		query.setParameter("secuencia", secuencia);
		query.setParameter("tipoTransaccion", idTipoTransaccion);
		query.setParameter("idTransaccion", idTransaccion);

		query.executeUpdate();
	}

	@Override
	public List<TransaccionRecaudacion> obtenerTransaccionesPorCliente(String cedula) {
		Query query = em.createNamedQuery("TransaccionRecaudacion.obtenerTransaccionPorCedula");
		query.setParameter(CEDULA, cedula);
		return query.getResultList();
	}

	@Override
	public BigDecimal saldoCapitalTotal(String cedula) {
		StringBuilder querySql=new StringBuilder();
		querySql.append( " select NVL(sum(NVL(rectb.VT_VALOR,0)),0) from  rec_valor_transaccion_env_tbl rectb,REC_TRANSACCION_TBL rec");
		querySql.append(" where rectb.VT_IDTRANSACCION=rec.TR_IDTRANSACCION and rectb.VT_IDTIPOTRANSACCION=rec.TR_IDTIPOTRANSACCION ");
		querySql.append( "and rec.TR_CEDULA=:cedula and rec.TR_ESTADO='EAM' and rectb.VT_CONCEPTO in ('AKP','CAP')");
		Query query = em.createNativeQuery(querySql.toString());
				query.setParameter("cedula", cedula);

		return (BigDecimal)query.getSingleResult();
	}
	
	
	@Override
	public TransaccionRecaudacion obtenerTransaccion(BigDecimal nut, String cedula,BigDecimal anioPerio,BigDecimal mesPeriod) {
		Query query = em.createNamedQuery("TransaccionRecaudacion.consultarTransPeriodo");
		query.setParameter("nut", nut);
		query.setParameter(CEDULA, cedula);
		query.setParameter("anioPeriodo", anioPerio);
		query.setParameter("mesPeriodo", mesPeriod);
		TransaccionRecaudacion transaccion;
	
		   List<TransaccionRecaudacion>	transacciones =  query.getResultList();
		   
		   if(transacciones!=null && transacciones.size()>0) {
			   //tomo la primera transaccion en EPL
			   transaccion=transacciones.get(0);
		   }else {
			transaccion = null;
		}

		return transaccion;
	}

	public boolean tieneMoraEmpleado( String cedula) {
		StringBuilder querySql=new StringBuilder();
  querySql.append("select  count(tr_cedula) as numafi from rec_transaccion_tbl")
         .append("  where tr_estado = 'EPL'")
         .append(" AND tr_cedula=:cedula")
         .append(" and tr_idtipotransaccion in ( 14,15)")
         .append(" and trunc(tr_fechamaximapago) < trunc(sysdate)")
         .append(" and (tr_aniper = 2020 and tr_mesper not in ( 7,8))");
		Query query = em.createNativeQuery(querySql.toString());
				query.setParameter("cedula", cedula);
		BigDecimal numTransacciones= (BigDecimal) query.getResultList().get(0);
		
		if(numTransacciones.intValue()>0) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
		
		


	}

}
