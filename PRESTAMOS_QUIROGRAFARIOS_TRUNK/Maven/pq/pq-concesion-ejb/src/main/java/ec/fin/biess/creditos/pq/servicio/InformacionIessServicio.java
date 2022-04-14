package ec.fin.biess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;

/**
 * Servicio para obtener informacion de los servicios web del IESS
 * 
 * @author hugo.mora
 *
 */
@Local
public interface InformacionIessServicio {

	/**
	 * Obtiene informacion de los servicios web del IESS
	 * 
	 * @param informacionIessServicio
	 * @param tipoInfoServicioIessEnum
	 * @return
	 * @throws PrecalificacionExcepcion
	 */
	InformacionIessServicioDto obtenerInformacion(InformacionIessServicioDto informacionIessServicio,
			TipoInformacionServicioIessEnum tipoInfoServicioIessEnum) throws PrecalificacionExcepcion;

}
