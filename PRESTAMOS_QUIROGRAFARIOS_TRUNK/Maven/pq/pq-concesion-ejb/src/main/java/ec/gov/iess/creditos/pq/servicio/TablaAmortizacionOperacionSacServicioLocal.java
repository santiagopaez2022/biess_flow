package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.DividendoDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;

@Local
public interface TablaAmortizacionOperacionSacServicioLocal {

	List<DividendoDto> obtenerTablaAmortizacionOperacionSac(OperacionSacRequest request) throws TablaAmortizacionSacException;

	TablaAmortizacionSac obtenerTablaAmortizacionSac(OperacionSacRequest request) throws TablaAmortizacionSacException;

}
