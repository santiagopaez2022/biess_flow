/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.sp.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ec.gov.iess.creditos.comprobante.dto.DatoComprobante;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoIndividualExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionAnticipadaExcepcion;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.sp.CalcularLiquidacion;
import ec.gov.iess.creditos.sp.GenerarComprobantePago;
import ec.gov.iess.creditos.sp.GenerarComprobantePagoIndividual;
import ec.gov.iess.creditos.sp.ComprobantePagoIndividualSacSp;
import ec.gov.iess.creditos.sp.GenerarComprobanteLiquidacionSp;
import ec.gov.iess.creditos.sp.LiquidacionAnticipada;
import ec.gov.iess.creditos.sp.LiquidacionJdbc;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.2 $  [Sep 17, 2007]
 * @author pablo
 */
@Stateless
public class LiquidacionJdbcImpl implements LiquidacionJdbc {

	@Resource(mappedName = "java:credito-pq-DS")
	DataSource dataSource;

	@Override
	public CalculoLiquidacion calcularLiquidacion(final PrestamoPk prestamoPk, final TipoLiquidacion tipoLiquidacion)
			throws CalculoLiquidacionExcepcion {

		final CalcularLiquidacion procCalcularLiquidacion = new CalcularLiquidacion(dataSource);
		final Map results = procCalcularLiquidacion.execute(prestamoPk, tipoLiquidacion);
		final String codigoError = (String) results.get("AOCRESPRO");
		final String mensajeError = (String) results.get("AOCMENERR");
		if (!"1".equals(codigoError.trim())) {
			throw new CalculoLiquidacionExcepcion(mensajeError);
		}
		
		final CalculoLiquidacion calculoLiquidacion = new CalculoLiquidacion();
		calculoLiquidacion.setCapitalTotal((BigDecimal) results.get("AONACUDIVTOT"));
		calculoLiquidacion.setEstadoPrestamo((String) results.get("AOCCODESTPRE"));
		calculoLiquidacion.setFechaFinal((Date) results.get("AOCFECFIN"));
		calculoLiquidacion.setFechaInicial((Date) results.get("AOCFECINI"));
		calculoLiquidacion.setInteresPorMora((BigDecimal) results.get("AONINTMORACU"));
		calculoLiquidacion.setMontoPrestamo((BigDecimal) results.get("AONMONPRE"));
		calculoLiquidacion.setSeguroSaldos((BigDecimal) results.get("AONVALSEGSALACU"));
		calculoLiquidacion.setValorPorCancelar((BigDecimal) results.get("AONTOTCANLIQ"));
		return calculoLiquidacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.sp.LiquidacionJdbc#generarLiquidacionAnticipada(ec.gov.iess.creditos.modelo.persistencia.pk.
	 * PrestamoPk, java.lang.String, ec.gov.iess.creditos.enumeracion.TipoLiquidacion, java.lang.String)
	 */
	@Override
	public BigDecimal generarLiquidacionAnticipada(final PrestamoPk prestamoPk, final String estadoPrestamo,
			final TipoLiquidacion tipoLiquidacion, final String tipoEmpleador) throws LiquidacionAnticipadaExcepcion {
		final LiquidacionAnticipada procedureLiquidacionAnticipada = new LiquidacionAnticipada(dataSource);
		final Map results = procedureLiquidacionAnticipada.execute(prestamoPk, estadoPrestamo, tipoLiquidacion, tipoEmpleador);
		final String codigoError = (String) results.get("AOCRESPRO");
		final String mensajeError = (String) results.get("AOCMENERR");
		if (!"1".equals(codigoError.trim())) {
			throw new LiquidacionAnticipadaExcepcion(mensajeError);
		}
		final BigDecimal numeroLiquidacion = (BigDecimal) results.get("AONNUMLIQPRE");
		return numeroLiquidacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.sp.LiquidacionJdbc#generarComprobanteDePago(ec.gov.iess.creditos.modelo.persistencia.pk.
	 * PrestamoPk, java.lang.String, java.math.BigDecimal, java.lang.String)
	 */
	@Override
	public ComprobantePagoPk generarComprobanteDePago(final PrestamoPk prestamoPk, final String cedula, final BigDecimal numeroLiquidacion, final String tipoEmpleador)
			throws GenerarComprobantePagoExcepcion {
		final GenerarComprobantePago proGenerarComprobantePago = new GenerarComprobantePago(dataSource);
		final Map results = proGenerarComprobantePago.execute(prestamoPk, cedula, numeroLiquidacion, tipoEmpleador);
		final String codigoError = (String) results.get("AOCRESPRO");
		final String mensajeError = (String) results.get("AOCMENERR");
		if (!"1".equals(codigoError.trim())) {
			throw new GenerarComprobantePagoExcepcion(mensajeError);
		}
		final String codigoComprobante = (String) results.get("AOCCODCOMPAG");
		final String tipoComprobante = (String) results.get("AOCCODTIPCOM");

		final ComprobantePagoPk comprobantePagoAfiliadoPk = new ComprobantePagoPk();
		comprobantePagoAfiliadoPk.setCodComPagAfi(codigoComprobante);
		comprobantePagoAfiliadoPk.setCodTipComPag(tipoComprobante);

		return comprobantePagoAfiliadoPk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.sp.LiquidacionJdbc#generarComprobantePagoIndividual(ec.gov.iess.creditos.modelo.persistencia
	 * .pk.PrestamoPk, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public ComprobantePagoPk generarComprobantePagoIndividual(final PrestamoPk prestamoPk, final String cedula, final String tipoPeriodo, final List<Long> dividendos,
			final String politicaCorporativa, final String tipoEmpleador) throws GenerarComprobantePagoIndividualExcepcion {
		final GenerarComprobantePagoIndividual procedure = new GenerarComprobantePagoIndividual(dataSource);

		final Map results = procedure.execute(prestamoPk, cedula, tipoPeriodo, dividendos, politicaCorporativa, tipoEmpleador);
		final String codigoError = (String) results.get("AOCRESPRO");
		final String mensajeError = (String) results.get("AOCMENERR");
		if (!"1".equals(codigoError.trim())) {
			throw new GenerarComprobantePagoIndividualExcepcion(mensajeError);
		}
		final String codigoComprobante = (String) results.get("AOCCODCOMPAG");
		final String tipoComprobante = (String) results.get("AOCCODTIPCOMPAG");

		final ComprobantePagoPk comprobantePagoAfiliadoPk = new ComprobantePagoPk();
		comprobantePagoAfiliadoPk.setCodComPagAfi(codigoComprobante);
		comprobantePagoAfiliadoPk.setCodTipComPag(tipoComprobante);

		return comprobantePagoAfiliadoPk;
	}
	
	@Override
	public ComprobantePagoPk generarComprobanteConsolidado(final PrestamoPk prestamoPk, final String cedula, final String tipoPeriodo, final List<Long> dividendos,
			final String politicaCorporativa, final String tipoEmpleador, 
			final String estadoPrestamo, final String tipoRecaudacion, final Date fechaVencimiento) throws GenerarComprobantePagoIndividualExcepcion {
		final GenerarComprobanteLiquidacionSp procedure = new GenerarComprobanteLiquidacionSp(dataSource);

		final Map results = procedure.execute(prestamoPk, cedula, tipoPeriodo, dividendos, politicaCorporativa, tipoEmpleador,
				estadoPrestamo, tipoRecaudacion, fechaVencimiento, null);
		final String codigoError = (String) results.get("ao_crespro");
		final String mensajeError = (String) results.get("ao_cmenerr");
		if (!"1".equals(codigoError.trim())) {
			throw new GenerarComprobantePagoIndividualExcepcion(mensajeError);
		}
		final String codigoComprobante = (String) results.get("ao_ccodcompag");
		final String tipoComprobante = (String) results.get("ao_ccodtipcompag");
		
		final ComprobantePagoPk comprobantePagoAfiliadoPk = new ComprobantePagoPk();
		comprobantePagoAfiliadoPk.setCodComPagAfi(codigoComprobante);
		comprobantePagoAfiliadoPk.setCodTipComPag(tipoComprobante);

		return comprobantePagoAfiliadoPk;
	}
	
	@Override
	public Map generarLiquidacionConsolidado(final PrestamoPk prestamoPk, 
			final String cedula, final String estadoPrestamo,
			final TipoLiquidacion tipoLiquidacion, final String tipoEmpleador,
			final String tipoRecaudacion, final Date fechaVencimiento) throws LiquidacionAnticipadaExcepcion {
		final GenerarComprobanteLiquidacionSp procedureLiquidacionAnticipada = new GenerarComprobanteLiquidacionSp(dataSource);
		final Map results = procedureLiquidacionAnticipada.execute(prestamoPk, cedula, null, null, null, tipoEmpleador,
					estadoPrestamo, tipoRecaudacion, fechaVencimiento, tipoLiquidacion.name());
		final String codigoError = (String) results.get("ao_crespro");
		final String mensajeError = (String) results.get("ao_cmenerr");
		if (codigoError == null) {
			throw new LiquidacionAnticipadaExcepcion("Proceso no termimo correctamente, comuniquese con el administrador de base de datos");
		} else {
			if (!"1".equals(codigoError.trim())) {
				throw new LiquidacionAnticipadaExcepcion(mensajeError);
			}
		}
		
		return results;
	}


	@Override
	public ComprobantePagoPk generarComprobantePagoIndividualSAC(final DatoComprobante datoComprobante) throws GenerarComprobantePagoIndividualExcepcion {
		final ComprobantePagoIndividualSacSp procedure = new ComprobantePagoIndividualSacSp(dataSource);

		final Map<String, Object> results = procedure.execute(datoComprobante);
		final String codigoError = (String) results.get("aocrespro");
		final String mensajeError = (String) results.get("aocmenerr");

		if (!"1".equals(codigoError.trim())) {
			throw new GenerarComprobantePagoIndividualExcepcion(mensajeError);
		}

		final Long codigoComprobante = Long.valueOf(results.get("aocodcompag").toString());
		final String tipoComprobante = (String) results.get("aocodtipcompag");

		final ComprobantePagoPk comprobantePagoAfiliadoPk = new ComprobantePagoPk();
		comprobantePagoAfiliadoPk.setCodComPagAfi(codigoComprobante.toString());
		comprobantePagoAfiliadoPk.setCodTipComPag(tipoComprobante);

		return comprobantePagoAfiliadoPk;
	}

	
}