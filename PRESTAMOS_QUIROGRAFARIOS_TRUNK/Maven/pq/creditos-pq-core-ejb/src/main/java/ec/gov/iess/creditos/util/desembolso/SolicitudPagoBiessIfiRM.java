package ec.gov.iess.creditos.util.desembolso;

import java.sql.ResultSet;
import java.sql.SQLException;

import ec.gov.iess.creditos.modelo.dto.SolicitudPago;

public class SolicitudPagoBiessIfiRM extends SolicitudPagoRM {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SolicitudPago solicitudPago = (SolicitudPago) super.mapRow(rs, rowNum);
		solicitudPago.setDesembolsoIfi(rs.getBigDecimal(20));
		String ctaIfi = rs.getString(21);
		if (ctaIfi == null) {
			solicitudPago.setNumeroCuentaIfiAcreedora(null);
		} else {
			solicitudPago.setNumeroCuentaIfiAcreedora(Long.parseLong(ctaIfi));
		}
		solicitudPago.setTipoCuentaIfiAcreedora(rs.getString(22));
		solicitudPago.setRucIfiAcreedora(rs.getString(23));
		solicitudPago.setTipoGarantiaIfi(rs.getString(24));
		return solicitudPago;
	}

}
