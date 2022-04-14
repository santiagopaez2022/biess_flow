package ec.gov.iess.creditos.util.desembolso.reverso;

import java.sql.ResultSet;
import java.sql.SQLException;

import ec.gov.iess.creditos.modelo.dto.SolicitudPago;

public class SolicitudPagoBiessIfiReversoRM extends SolicitudPagoReversoRM {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SolicitudPago solicitudPago = (SolicitudPago) super.mapRow(rs, rowNum);
		solicitudPago.setDesembolsoIfi(rs.getBigDecimal(24));
		String ctaIfi = rs.getString(25);
		if (ctaIfi == null) {
			solicitudPago.setNumeroCuentaIfiAcreedora(null);
		} else {
			solicitudPago.setNumeroCuentaIfiAcreedora(Long.parseLong(ctaIfi));
		}
		solicitudPago.setTipoCuentaIfiAcreedora(rs.getString(26));
		solicitudPago.setRucIfiAcreedora(rs.getString(27));
		solicitudPago.setTipoGarantiaIfi(rs.getString(28));
		return solicitudPago;
	}

}
