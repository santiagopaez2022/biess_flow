package ec.gov.iess.creditos.util.desembolso;

import java.sql.ResultSet;
import java.sql.SQLException;

import ec.gov.iess.creditos.modelo.dto.SolicitudPago;

public class SolicitudPagoShRM extends SolicitudPagoRM {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SolicitudPago solicitudPago = (SolicitudPago) super.mapRow(rs, rowNum);
		solicitudPago.setTipoGarantiaIfi(rs.getString(20));
		return solicitudPago;
	}

}
