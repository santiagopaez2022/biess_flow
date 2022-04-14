package ec.gov.iess.planillaspq.storeprocedure.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.storeprocedure.CambioEstadoComprobanteParAnuSpLocal;


@Stateless
public class CambioEstadoComprobanteParAnuSpImpl implements
CambioEstadoComprobanteParAnuSpLocal {
	@Resource(mappedName = "java:/pqintranetDS")
	DataSource dataSource;
	String menerr;
	String coderr;
	public CambioEstadoComprobanteParAnuSpImpl() {
		super();
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Map ejecutarCambioEstadoComprobanteParAnu(ComprobantePagoPla compag, String Observacion,String cedfun,String nueest) throws EjecucionProcedimientoException {
		 
			EjecucionProcesoCambioEstado ejecucion = new EjecucionProcesoCambioEstado(dataSource);
	        return ejecucion.ejecutar(compag,Observacion,cedfun,nueest);
	        //Map results = ejecucion.ejecutar(pla);
	        //coderr=(String)results.get("AOCRESPRO");
	        //menerr=(String)results.get("AOCMENERR");

	}

	private class EjecucionProcesoCambioEstado extends StoredProcedure {

		private static final String SQL = "ksreckcompagesthis.actreghisestcompagparanu";

		public EjecucionProcesoCambioEstado(DataSource ds) {
			setDataSource(ds);
			setSql(SQL);
			declareParameter(new SqlParameter("AICCODTIPCOMPAG", Types.VARCHAR));
			declareParameter(new SqlParameter("AICCODESTANTCOMPAG", Types.VARCHAR));
			declareParameter(new SqlParameter("AICCODESTNUECOMPAG", Types.VARCHAR));
			declareParameter(new SqlParameter("AICCODCOMPAG", Types.VARCHAR));
			declareParameter(new SqlParameter("AIDFECFIN", Types.DATE));
			declareParameter(new SqlParameter("AICOBSTRA", Types.VARCHAR));
			declareParameter(new SqlParameter("AICCODUSU", Types.VARCHAR));
            declareParameter(new SqlOutParameter("AOCRESPRO", Types.CHAR));
            declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
			compile();
		}

		@SuppressWarnings("unchecked")
		Map ejecutar(ComprobantePagoPla compag, String Observacion,String cedfun,String nueest) {
            Map parin = new HashMap();
    		java.util.Date fechahoy=new java.util.Date();

            parin.put("AICCODTIPCOMPAG", compag.getPk().getCodtipcompag());
            parin.put("AICCODESTANTCOMPAG", compag.getCodestcompag());
            parin.put("AICCODESTNUECOMPAG", nueest);
            parin.put("AICCODCOMPAG", compag.getPk().getCodcompag());
            parin.put("AIDFECFIN",fechahoy);
            parin.put("AICOBSTRA", Observacion);
            parin.put("AICCODUSU",cedfun);
            return execute(parin);
		}
	}

}
