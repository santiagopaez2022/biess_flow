package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.dao.ResumenCreditoDao;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DetalleCalculoIngresos;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.ResumenCreditoPQ;
import ec.gov.iess.creditos.pq.servicio.ResumenCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.ResumenCreditoServicioRemote;



/**
 * @author jasanchez
 * 
 */
@Stateless
public class ResumenCreditoServicioImpl implements ResumenCreditoServicioRemote, ResumenCreditoServicio {
	@SuppressWarnings("unused")
	private static final LoggerBiess log = LoggerBiess.getLogger(ResumenCreditoServicioImpl.class);
	private static final long serialVersionUID = 1L;
   
	@EJB
	ResumenCreditoDao resumenCreditoDao;
	
	@EJB
	PrestamoDao prestamoDao;
	
	
    public ResumenCreditoServicioImpl() {

    }

	public void registrarResumenCredito(DatosCredito credito,Prestamo prestamo) {
		ResumenCreditoPQ resumenCredito = null;
		try {
			if (resumenCredito == null) {
				resumenCredito = new ResumenCreditoPQ();
				resumenCredito.setRpqRucinsfinanciera(prestamo.getRucempinsfin().trim());
				resumenCredito.setRpqNumcuenta(prestamo.getNumctaban());
				resumenCredito.setRpqRucempleador(credito.getEmpleador().getRucEmpleador().trim());
				resumenCredito.setRpqEdad((long) credito.getCalculoCredito().getCondicionCalculo().getEdadActualAnios());
				resumenCredito.setRpqPlzmaximo((long) credito.getCalculoCredito().getCondicionCalculo().getPlazoMaximo());
				resumenCredito.setRpqTasaint(credito.getCalculoCredito().getCondicionCalculo().getTasaInteres());
				resumenCredito.setRpqSueldoprom(credito.getCalculoCredito().getGarantia().getSueldoPromedioOriginal());
				resumenCredito.setRpqCappago(credito.getCalculoCredito().getGarantia().getCapacidadPago());
				resumenCredito.setRpqCapendeudamiento(credito.getCalculoCredito().getGarantia().getCapacidadEndeudamiento());
				resumenCredito.setRpqCuootrospres(new BigDecimal(0));
				List<DetalleCalculoIngresos> detallesCalculoIngresos = credito.getCalculoCredito().getGarantia().getDetalleCalculoIngresos();
				if (detallesCalculoIngresos != null)
				for (int i = 0; i < detallesCalculoIngresos.size(); i++) {
					DetalleCalculoIngresos detalleCalculoIngresos = detallesCalculoIngresos.get(i);
					if (detalleCalculoIngresos != null) {
						if ("Cesantia".equals(detalleCalculoIngresos.getObservacion())){
							if ("Comprometidas".equals(detalleCalculoIngresos.getNombre())){
								resumenCredito.setRpqValcomces(detalleCalculoIngresos.getValor());

							}
							if ("Total".equals(detalleCalculoIngresos.getNombre())){
								resumenCredito.setRpqTotcesantia(detalleCalculoIngresos.getValor());

							}
							if ("Disponible".equals(detalleCalculoIngresos.getNombre())){
								resumenCredito.setRpqValdisces(detalleCalculoIngresos.getValor());
							
							}
						}
						if ("Fondos".equals(detalleCalculoIngresos.getObservacion())){
							if ("Comprometidas".equals(detalleCalculoIngresos.getNombre())){
								resumenCredito.setRpqValcomfr(detalleCalculoIngresos.getValor());
							}
							if ("Total".equals(detalleCalculoIngresos.getNombre())){
								resumenCredito.setRpqTotfr(detalleCalculoIngresos.getValor());
							}
							if ("Disponible".equals(detalleCalculoIngresos.getNombre())){
								resumenCredito.setRpqValdisfr(detalleCalculoIngresos.getValor());
							}
						}
					}					
				}
				
				resumenCredito.setRpqNumaportaciones(credito.getCalculoCredito().getGarantia().getNumImposicionesCon());
				resumenCredito.setRpqNumaportacionescon(credito.getCalculoCredito().getGarantia().getNumImposiciones());
				
				resumenCredito.setRpqTotdisgar(credito.getCalculoCredito().getGarantia().getTotalGarantia());
				
				resumenCredito.setRpqNumtotpq(new Long(credito.getCalculoCredito().getGarantia().getNumTotPQVig()));				
				resumenCredito.setRpqNumtotph(new Long(credito.getCalculoCredito().getGarantia().getNumTotPHVig()));
								
				
				resumenCredito.setRpqMontotdivpq(credito.getCalculoCredito().getGarantia().getSumDivPq());
				resumenCredito.setRpqTotmonconpq(credito.getCalculoCredito().getGarantia().getSumMntPq());
							
				resumenCredito.setRpqMontotdivph(credito.getCalculoCredito().getGarantia().getSumDivPh());
				resumenCredito.setRpqTotmonconph(credito.getCalculoCredito().getGarantia().getSumMntPh());				
				
				resumenCredito.setRpqSegdesgra(new BigDecimal(prestamo.getValsegsal()));
				
				if (credito.isEsNovacion()){
					resumenCredito.setRpqMontoliquido(new BigDecimal(prestamo.getMntpre()).subtract(new BigDecimal(prestamo.getValsegsal())).subtract(new BigDecimal(prestamo.getValliqnov())));
				} else {
					resumenCredito.setRpqMontoliquido(new BigDecimal(prestamo.getMntpre()).subtract(new BigDecimal(prestamo.getValsegsal())));
				}
				resumenCredito.setRpqPlazo(prestamo.getPlzmes());
				resumenCredito.setRpqInteres(new BigDecimal( prestamo.getIntdiagrc()));
				BigDecimal sumaInteres = new BigDecimal(0);
				BigDecimal sumaCapital = new BigDecimal(0);
				
				for (DividendoCalculo dividendoCalculo : credito
						.getDividendoCalculoList()) {
					sumaInteres = sumaInteres.add(dividendoCalculo.getValorInteres());	
					sumaCapital = sumaCapital.add(dividendoCalculo.getValorDividendo());
				}
				resumenCredito.setRpqMontocredito(new BigDecimal(prestamo.getMntpre()).add(sumaInteres));
				resumenCredito.setRpqTotcapital(sumaCapital);
				resumenCredito.setRpqTotinteres(sumaInteres);
				resumenCredito.setRpqFecRegistro(new Date());
				//resumenCredito.setRpqAnuJub(error);
				
				//resumenCredito.setPrestamo(prestamo);
				
				resumenCredito.setNumpreafi(prestamo.getPrestamoPk().getNumpreafi());
				resumenCredito.setCodprecla(prestamo.getPrestamoPk().getCodprecla());
				resumenCredito.setOrdpreafi(prestamo.getPrestamoPk().getOrdpreafi());
				resumenCredito.setCodpretip(prestamo.getPrestamoPk().getCodpretip());
				String tipoAfiliado = "JUBILADO";
				if (new Long(20).equals(prestamo.getPrestamoPk().getCodprecla())) {
					tipoAfiliado = "AFILIADO";
				} else if (new Long(22).equals(prestamo.getPrestamoPk().getCodprecla())) {
					tipoAfiliado = "ZAFRERO";
				} else {
					tipoAfiliado = "JUBILADO";
				}
				resumenCredito.setRpqTipoafiliado(tipoAfiliado);
				
				resumenCreditoDao.ingresar(resumenCredito);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	

}
