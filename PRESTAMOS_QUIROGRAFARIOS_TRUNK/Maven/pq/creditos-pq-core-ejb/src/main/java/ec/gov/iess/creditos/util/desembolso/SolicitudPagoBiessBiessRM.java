package ec.gov.iess.creditos.util.desembolso;

import java.sql.ResultSet;
import java.sql.SQLException;

import ec.gov.iess.creditos.modelo.dto.SolicitudPago;

public class SolicitudPagoBiessBiessRM extends SolicitudPagoRM {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SolicitudPago solicitudPago = (SolicitudPago) super.mapRow(rs, rowNum);
		solicitudPago.setNutOpCancelada(rs.getLong(20));
		solicitudPago.setValorCapitalOpCancelada(rs.getBigDecimal(21));
		solicitudPago.setValorInteresOpCancelada(rs.getBigDecimal(22));
		solicitudPago.setValorPrimasSegIngOpCancelada(rs.getBigDecimal(23));
		solicitudPago.setValorPrimasSegDesOpCancelada(rs.getBigDecimal(24));
		solicitudPago.setValorInteresMoraOpCancelada(rs.getBigDecimal(25));
		solicitudPago.setCancelacionOperacion(rs.getBigDecimal(26));
		solicitudPago.setValorImpuestosOpCancelada(rs.getBigDecimal(27));
		solicitudPago.setValorSegVidaOpCancelada(rs.getBigDecimal(28));
		return solicitudPago;
	}

}
