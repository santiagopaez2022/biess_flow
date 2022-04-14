package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.DatoDeudaDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.SimulacionValorExigibleException;

@Local
public interface SimulacionValorExigibleMontoSacServicioLocal {

	List<DatoDeudaDto> obtenerDividendos(OperacionSacRequest operacion) throws SimulacionValorExigibleException;

	CreditoExigibleDto obtenerSimulacionExigibleValor(OperacionSacRequest operacion) throws SimulacionValorExigibleException;

}
