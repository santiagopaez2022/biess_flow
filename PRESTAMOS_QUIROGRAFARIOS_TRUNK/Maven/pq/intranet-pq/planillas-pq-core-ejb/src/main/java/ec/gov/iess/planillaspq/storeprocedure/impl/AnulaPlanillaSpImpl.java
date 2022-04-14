package ec.gov.iess.planillaspq.storeprocedure.impl;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.storeprocedure.AnulaPlanillaSpLocal;
import ec.gov.iess.planillaspq.storeprocedure.AnulaPlanillaSpRemote;


@Stateless
public class AnulaPlanillaSpImpl  implements
AnulaPlanillaSpLocal, AnulaPlanillaSpRemote  {
	@Resource(mappedName = "java:/pqintranetDS")
	DataSource dataSource;
	String menerr;
	String coderr;
	public AnulaPlanillaSpImpl() {
		super();
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Map ejecutarAnulacionPlanillas(Planillas pla) throws EjecucionProcedimientoException {
		 
			EjecucionProcesoAnulacion ejecucion = new EjecucionProcesoAnulacion(dataSource);
	        return ejecucion.ejecutar(pla);
	        //Map results = ejecucion.ejecutar(pla);
	        //coderr=(String)results.get("AOCRESPRO");
	        //menerr=(String)results.get("AOCMENERR");

	}

	private class EjecucionProcesoAnulacion extends StoredProcedure {

		private static final String SQL = "ksreckplaregesthis.actreghisestpla";

		public EjecucionProcesoAnulacion(DataSource ds) {
			setDataSource(ds);
			setSql(SQL);
			declareParameter(new SqlParameter("AICRUCEMP", Types.VARCHAR));
			declareParameter(new SqlParameter("AICCODSUC", Types.VARCHAR));
			declareParameter(new SqlParameter("AICCODTIPPLA", Types.VARCHAR));
			declareParameter(new SqlParameter("AICTIPPER", Types.VARCHAR));
			declareParameter(new SqlParameter("AINANIPER", Types.NUMERIC));
			declareParameter(new SqlParameter("AINMESPER", Types.NUMERIC));
			declareParameter(new SqlParameter("AINSECPLA", Types.NUMERIC));
			declareParameter(new SqlParameter("AICCODESTANTPLA", Types.VARCHAR));
			declareParameter(new SqlParameter("AICCODESTNUEPLA", Types.VARCHAR));
			declareParameter(new SqlParameter("AIDFECFIN", Types.DATE));
            declareParameter(new SqlOutParameter("AOCRESPRO", Types.CHAR));
            declareParameter(new SqlOutParameter("AOCMENERR", Types.VARCHAR));
			compile();
		}

		@SuppressWarnings("unchecked")
		Map ejecutar(Planillas pla) {
            Map parin = new HashMap();
            parin.put("AICRUCEMP", pla.getPk().getRucemp());
            parin.put("AICCODSUC", pla.getPk().getCodsuc());
            parin.put("AICCODTIPPLA", pla.getPk().getCodtippla());
            parin.put("AICTIPPER", pla.getPk().getTipper());
            parin.put("AINANIPER",pla.getPk().getAniper());
            parin.put("AINMESPER", pla.getPk().getMesper());
            parin.put("AINSECPLA",pla.getPk().getSecpla());
            parin.put("AICCODESTANTPLA", pla.getEsttippla());
            parin.put("AICCODESTNUEPLA", "ANU");
            parin.put("AIDFECFIN", new Date());
            return execute(parin);
		}
	}

}
