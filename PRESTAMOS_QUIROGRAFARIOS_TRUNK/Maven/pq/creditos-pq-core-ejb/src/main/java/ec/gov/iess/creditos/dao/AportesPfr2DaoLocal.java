package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.AportesPfr2;
import ec.gov.iess.dao.GenericDao;

@Local
public interface AportesPfr2DaoLocal extends GenericDao<AportesPfr2, BigInteger>{


	public abstract List<AportesPfr2> findByCedulaAportesComprometidosPq(String cedula);
	
	public List<AportesPfr2> findByCedulaAndPrestamoAportesComprometidosPq(String cedula,
			Long numpreafi,
			Long codpretip,
			Long ordpreafi,
			Long codprecla);
	
	public BigDecimal obtenerTotalFr(String cedula);
}