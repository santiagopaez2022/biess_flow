package ec.gov.iess.creditos.util.desembolso.reverso;

import java.sql.ResultSet;
import java.sql.SQLException;

import ec.gov.iess.creditos.modelo.dto.SolicitudPago;

public class SolicitudPagoBiessBiessReversoRM extends SolicitudPagoReversoRM {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SolicitudPago solicitudPago = (SolicitudPago) super.mapRow(rs, rowNum);
		solicitudPago.setNutOpCancelada(rs.getLong(24));
		solicitudPago.setValorCapitalOpCancelada(rs.getBigDecimal(25));
		solicitudPago.setValorInteresOpCancelada(rs.getBigDecimal(26));
		solicitudPago.setValorPrimasSegIngOpCancelada(rs.getBigDecimal(27));
		solicitudPago.setValorPrimasSegDesOpCancelada(rs.getBigDecimal(28));
		solicitudPago.setValorInteresMoraOpCancelada(rs.getBigDecimal(29));
		solicitudPago.setCancelacionOperacion(rs.getBigDecimal(30));
		solicitudPago.setValorImpuestosOpCancelada(rs.getBigDecimal(31));
		solicitudPago.setValorSegVidaOpCancelada(rs.getBigDecimal(32));
		return solicitudPago;
	}

}
