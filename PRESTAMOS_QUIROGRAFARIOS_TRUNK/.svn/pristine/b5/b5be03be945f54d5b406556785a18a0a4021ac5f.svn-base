package ec.gov.iess.creditos.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;




public class AnularComprobantePagoSp extends StoredProcedure {

	private static final String SQL = "KSCREKCOMPAGAFIESTHIS.ACTREGHISESTCOMPAGAFI";
	
	

	public AnularComprobantePagoSp(DataSource ds) {
		setDataSource(ds);
		setSql(SQL);

		// Parametros de entrada
		declareParameter(new SqlParameter("aiccodtipcompag", Types.VARCHAR));
		declareParameter(new SqlParameter("aiccodcompagafi", Types.VARCHAR));
		declareParameter(new SqlParameter("aiccodestantcompag", Types.VARCHAR));
		declareParameter(new SqlParameter("aiccodestnuecompag", Types.VARCHAR));
		declareParameter(new SqlParameter("aicfecfin", Types.VARCHAR));
		declareParameter(new SqlParameter("aicobstra", Types.VARCHAR));

		// Parametros de salida
		declareParameter(new SqlOutParameter("aocrespro", Types.VARCHAR));
		declareParameter(new SqlOutParameter("aocmenerr", Types.VARCHAR));
	}

	@SuppressWarnings("unchecked")
	public Map execute(ComprobantePago comprobantePago, String estado, String fecha, String observacion) {
		Map input = new HashMap();
		input.put("aiccodtipcompag", comprobantePago.getPk().getCodTipComPag());
		input.put("aiccodcompagafi", comprobantePago.getPk().getCodComPagAfi());
		input.put("aiccodestantcompag", comprobantePago.getEstadoComprobante().name());
		input.put("aiccodestnuecompag", estado);
		input.put("aicfecfin", fecha);
		input.put("aicobstra", observacion);
		
		return execute(input);
	}
}