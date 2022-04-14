package ec.gov.iess.creditos.util.desembolso;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ec.gov.iess.creditos.modelo.dto.SolicitudPago;

public class SolicitudPagoRM implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SolicitudPago solicitudPago = new SolicitudPago();
		solicitudPago.setCedula(rs.getString(1));
		solicitudPago.setTipoSolicitud(rs.getString(2));
		solicitudPago.setProvincia(rs.getString(3));
		solicitudPago.setFechaEscritura(rs.getTime(4));
		solicitudPago.setMonto(rs.getBigDecimal(5));
		solicitudPago.setPlazo(rs.getLong(6));
		solicitudPago.setGastos(rs.getBigDecimal(7));
		solicitudPago.setDividendo(rs.getBigDecimal(8));
		solicitudPago.setNut(rs.getLong(9));
		solicitudPago.setCodigoAgenciaEmisora(rs.getString(10));
		solicitudPago.setVendedorDocumento(rs.getString(11));

		String cta = rs.getString(12);
		if (cta == null) {
			solicitudPago.setVendedorNumeroCuenta(null);
		} else {
			solicitudPago.setVendedorNumeroCuenta(Long.parseLong(cta));
		}

		solicitudPago.setVendedorTipoCuenta(rs.getString(13));
		solicitudPago.setVendedorNombre(rs.getString(14));
		solicitudPago.setVendedorCodigoBancoCuenta(rs.getString(15));
		// ------------------------------------------------------------------
		solicitudPago.setValorSeguroRiesgos(rs.getBigDecimal(16));
		solicitudPago.setValorAprobado(rs.getBigDecimal(17));
		solicitudPago.setValorFinanciado(rs.getBigDecimal(18));
		solicitudPago.setValorSeguroVida(rs.getBigDecimal(19));
		solicitudPago.setSeleccionado(true);
		return solicitudPago;
	}

}
