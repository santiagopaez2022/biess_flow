package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.DatoDeudaDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.SimulacionValorExigibleException;

/**
 * Servicio remoto para consumo de creditos pq exigibles desde core negocio
 * 
 * @author hugo.mora
 * @date 2018/09/03
 *
 */
@Remote
public interface SimulacionValorExigibleSacServicioRemoto {

	List<DatoDeudaDto> obtenerSimulacionValorExigible(OperacionSacRequest operacion) throws SimulacionValorExigibleException;

	CreditoExigibleDto obtenerSimulacionExigibles(OperacionSacRequest operacion) throws SimulacionValorExigibleException;

}
