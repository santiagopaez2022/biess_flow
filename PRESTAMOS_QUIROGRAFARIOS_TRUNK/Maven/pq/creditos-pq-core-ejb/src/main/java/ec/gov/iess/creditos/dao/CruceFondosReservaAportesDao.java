package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CruceFondosReservaAportes;
import ec.gov.iess.dao.GenericDao;

@Local
public interface CruceFondosReservaAportesDao extends GenericDao<CruceFondosReservaAportes, Long>{

	public List<CruceFondosReservaAportes> findByNumpreafiYCodigoAporte(String cedula,Long numpreafi,Long codprecla,Long codpretip,Long ordpreafi,BigInteger codigoaporte);
	public BigDecimal ObtenerValoresComprometidosFondosCruces(String cedula,Long numpreafi,Long codprecla,Long codpretip,Long ordpreafi);
}	