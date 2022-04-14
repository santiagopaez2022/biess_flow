/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DetalleCatalogosDao;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCatalogos;
import ec.gov.iess.creditos.pq.servicio.DetalleCatalogoServicio;

/**
 * @author Daniel Cardenas, pjarrin
 * 
 */
@Stateless
public class DetalleCatalogoServicioImpl implements DetalleCatalogoServicio {

	LoggerBiess log = LoggerBiess.getLogger(DetalleCatalogoServicioImpl.class);

	@EJB
	DetalleCatalogosDao detalleCatalogosDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DetalleCatalogoServicio#
	 * compruebaParentezcoBeneficiario(java.lang.String)
	 */
	public boolean compruebaParentezcoBeneficiario(String parentezco) {
		return detalleCatalogosDao.compruebaParentezcoBeneficiario(parentezco);
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.DetalleCatalogoServicio#encontrarDetalleCatalogo(java.lang.String, java.lang.String)
	 */
	public DetalleCatalogos encontrarDetalleCatalogo(String codigoCatalogo,
			String codigoDetalleCatalogo) {
		return detalleCatalogosDao.encontrarDetalleCatalogo(codigoCatalogo,
				codigoDetalleCatalogo);
	}
}