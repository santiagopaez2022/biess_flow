package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.dto.DividendoDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;


@Remote
public interface TablaAmortizacionOperacionSacServicioRemote {

	List<DividendoDto> obtenerTablaAmortizacionOperacionSac(OperacionSacRequest request) throws TablaAmortizacionSacException;

}
