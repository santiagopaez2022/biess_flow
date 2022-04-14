package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CuentaBancariaAprobadaDao;
import ec.gov.iess.creditos.dao.CuentaBancariaHistAprobadaDao;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.modelo.persistencia.CuentasBancariasAprobadas;
import ec.gov.iess.creditos.modelo.persistencia.CuentasBancariasHistAprobadas;
import ec.gov.iess.creditos.modelo.persistencia.pk.CuentasBancariasAprobadasHistPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.CuentasBancariasAprobadasPk;
import ec.gov.iess.creditos.pq.servicio.CuentaBancariaAprobadaServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CuentaBancariaAprobadaServicioRemote;

/**
 * @author Andres Cantos
 * 
 */
@Stateless
public class CuentaBancariaAprobadaServicioImpl implements CuentaBancariaAprobadaServicioLocal,
		CuentaBancariaAprobadaServicioRemote {
	private static LoggerBiess log = LoggerBiess.getLogger(CuentaBancariaAprobadaServicioImpl.class);

	@EJB
	CuentaBancariaAprobadaDao ctabancariaprobadadao;

	@EJB
	CuentaBancariaHistAprobadaDao ctabancariahistorico;

	public void actulizarctalistablanca(String cedula, String entidadfinanciera, String numerocuenta,
			String rucinstfin, TipoCuenta tipocta, String cedulafun, String estadoCredito) {
		List<CuentasBancariasAprobadas> lstctasapr = ctabancariaprobadadao.consultarCuentasporAfiliado(cedula);
		CuentasBancariasAprobadas ctabcaapr = null;
		/* Distinguir registros de lista blanca de tipo ECC */
		if (null == estadoCredito || estadoCredito.compareToIgnoreCase("ECC") != 0) {
			estadoCredito = "VALIDO";
		}
		if (lstctasapr != null && !lstctasapr.isEmpty()) {
			for (CuentasBancariasAprobadas cba : lstctasapr) {
				if (cba.getEstado().compareToIgnoreCase(estadoCredito) == 0) {
					ctabcaapr = cba;
					break;
				}				
			}
		}		
		String obs = estadoCredito.compareToIgnoreCase("ECC") == 0 ? "ECC" : "VALIDO";
		/* Crear nuevo registro en la lista blanca */
		if (ctabcaapr == null) {
			log.info("No existe una cuenta bancaria para el afiliado");
			log.info("Creando nueva cuenta bancaria en lista blanca");
			CuentasBancariasAprobadasPk ctaprapk = new CuentasBancariasAprobadasPk(new Date(), cedula);
			CuentasBancariasAprobadas ctapra = new CuentasBancariasAprobadas();
			ctapra.setCuentabancariapk(ctaprapk);
			ctapra.setEntidadfinanciera(entidadfinanciera);
			ctapra.setNumerocuenta(numerocuenta);
			ctapra.setRucinstfinanciera(rucinstfin);
			ctapra.setTipocta(tipocta);
			ctapra.setEstado(obs);
			ctabancariaprobadadao.insert(ctapra);
			// ingresar el historico con la cedula del funcionario
			// mismoregistro + cedula del funcionario
			CuentasBancariasAprobadasHistPk pkhsist = new CuentasBancariasAprobadasHistPk(new Date(), cedula);
			CuentasBancariasHistAprobadas historico = new CuentasBancariasHistAprobadas();
			historico.setCuentabancariahistpk(pkhsist);
			historico.setFechaanterior(ctapra.getCuentabancariapk().getFecha());
			historico.setFuncionario(cedulafun);
			historico.setEntidadfinanciera(entidadfinanciera);
			historico.setNumerocuenta(numerocuenta);
			historico.setRucinstfinanciera(rucinstfin);
			historico.setTipocta(tipocta);
			historico.setEstado("PRIMERA VEZ");
			ctabancariahistorico.insert(historico);
		/* Actualizar registro en la lista blanca */
		} else {
			// Guardar historico
			CuentasBancariasAprobadasHistPk pkhsist = new CuentasBancariasAprobadasHistPk(new Date(), cedula);
			CuentasBancariasHistAprobadas historico = new CuentasBancariasHistAprobadas();
			historico.setCuentabancariahistpk(pkhsist);
			historico.setFechaanterior(ctabcaapr.getCuentabancariapk().getFecha());
			historico.setFuncionario(cedulafun);
			historico.setEntidadfinanciera(ctabcaapr.getEntidadfinanciera());
			historico.setNumerocuenta(ctabcaapr.getNumerocuenta());
			historico.setRucinstfinanciera(ctabcaapr.getRucinstfinanciera());
			historico.setTipocta(ctabcaapr.getTipocta());
			historico.setEstado("DESACTUALIZADO");
			ctabancariahistorico.insert(historico);
			// Elimino registro antiguo
			ctabancariaprobadadao.delete(ctabcaapr);
			ctabancariaprobadadao.flush();
			// Inserto nuevo registro
			CuentasBancariasAprobadasPk pk = new CuentasBancariasAprobadasPk();
			pk.setCedula(cedula);
			pk.setFecha(new Date());
			CuentasBancariasAprobadas cba = new CuentasBancariasAprobadas();
			cba.setCuentabancariapk(pk);
			cba.setEntidadfinanciera(entidadfinanciera);
			cba.setNumerocuenta(numerocuenta);
			cba.setRucinstfinanciera(rucinstfin);
			cba.setTipocta(tipocta);
			cba.setEstado(obs);			
			ctabancariaprobadadao.insert(cba);
		}
	}

}
