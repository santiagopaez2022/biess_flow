package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import ec.gob.biess.planillaspq.web.exception.PagIndException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.SaldoLiquidacionPrestamo;

/**
 * Clase que ayuda a validar los dividendos seleccionados
 * 
 * @author Daniel Cardenas
 */
public class PagIndHelper implements Serializable {

	private static final long serialVersionUID = -3839118032232754935L;

	private transient static final LoggerBiess log = LoggerBiess.getLogger(PagIndHelper.class);

	/**
	 * Prepara la validacion de seleccion de dividendos
	 * 
	 * @param valores
	 * @param dividendosPorPagar
	 * @return
	 * @throws PagIndException
	 */
	public static List<Long> validarDividendos(String[] valores, List<DividendoPrestamo> dividendosPorPagar)
			throws PagIndException {

		List<Long> valoresValidados = validar(valores, dividendosPorPagar);
		return valoresValidados;
	}

	/**
	 * Prepara la validacion de seleccion de dividendos POR SALDOS
	 * 
	 * @param valores
	 * @param saldosPorPagar
	 * @return
	 * @throws PagIndException
	 */
	public static List<Long> validarDividendosSaldos(String[] valores, List<SaldoLiquidacionPrestamo> saldosPorPagar)
			throws PagIndException {
		// obitiene de la lista de "SaldoLiquidacionPrestamo" a una lista de
		// "DividendoPrestamo"
		List<DividendoPrestamo> dividendosPorPagar = new ArrayList<DividendoPrestamo>();
		for (SaldoLiquidacionPrestamo saldoLiquidacionPrestamo : saldosPorPagar) {
			dividendosPorPagar.add(saldoLiquidacionPrestamo.getDividendoPrestamo());
		}

		List<Long> valoresValidados = validar(valores, dividendosPorPagar);
		return valoresValidados;
	}

	/**
	 * Valida si la seleccion de dividendos a pagar es correcta
	 * 
	 * @param valores
	 * @param dividendosPorPagar
	 * @throws PagIndException
	 */
	private static List<Long> validar(String[] valores, List<DividendoPrestamo> dividendosPorPagar)
			throws PagIndException {
		// valida que siempre este el dividendo mas antiguo

		List<Long> valoresValidados = validarContinuos(valores, dividendosPorPagar);

		Long primerDividendo = dividendosPorPagar.get(0).getDividendoPrestamoPk().getNumdiv();
		Long primerDividendoEscogido = valoresValidados.get(0);

		if (primerDividendo.compareTo(primerDividendoEscogido) != 0) {
			String err = "Debe seleccionar el primer dividendo (numero=" + primerDividendo + ")";
			log.info(err);
			throw new PagIndException(err);
		}

		// valida que solo escoja un solo dividendo con estado REGISTRADO
		String[] estadosDiv = new String[valores.length];
		int numReg = 0;
		for (int i = 0; i < valores.length; i++) {
			if (obtenerEstadoDividendo(valoresValidados.get(i), dividendosPorPagar).getCodestdiv() != null) {
				estadosDiv[i] = obtenerEstadoDividendo(valoresValidados.get(i), dividendosPorPagar).getCodestdiv();
				if (estadosDiv[i].equals("REG")) {
					numReg++;
				}
			}
		}
		if (numReg > 1) {
			String err = "Debe seleccionar solo un dividendo en estado REGISTRADO.";
			log.info(err);
			throw new PagIndException(err);
		}

		return valoresValidados;
	}

	/**
	 * Verifica que los valores no sean saltados y que haya por lo menos uno
	 * 
	 * @param valores
	 * @param dividendosPorPagar
	 * @return
	 * @throws PagIndException
	 */
	private static List<Long> validarContinuos(String[] valoresSeleccionados, List<DividendoPrestamo> dividendosPorPagar)
			throws PagIndException {

		List<Long> dividendosAPagar = new ArrayList<Long>();

		if (valoresSeleccionados == null || valoresSeleccionados.length == 0) {
			log.info("no ha seleccionado nada");
			throw new PagIndException("Seleccione al menos un dividendo.");
		}

		log.info("numeros de dividendo:");

		for (int i = 0; i < valoresSeleccionados.length; i++) {
			dividendosAPagar.add(new Long(valoresSeleccionados[i]));
			log.info(valoresSeleccionados[i]);
			// si no es el ultimo (en el saco de que sea un arreglo de 1 no
			// entraria)
			if (i != (valoresSeleccionados.length - 1)) {

				Long loQueTieneElSiguiente = new Long(valoresSeleccionados[i + 1]);

				Long loQueDeberiaTenerElSiguiente = obtenerSiguienteNumDividendo(dividendosPorPagar, new Long(
						valoresSeleccionados[i]));

				log.info("loQueTieneElSiguiente:" + loQueTieneElSiguiente);
				log.info("loQueDeberiaTenerElSiguiente:" + loQueDeberiaTenerElSiguiente);
				if (loQueTieneElSiguiente.compareTo(loQueDeberiaTenerElSiguiente) != 0) {
					log.info("no son dividendos seguidos!");
					throw new PagIndException("Los dividendos deben ser continuos.");
				}
			}
		}

		return dividendosAPagar;
	}

	/**
	 * Obtiene el estado del dividendo
	 * 
	 * @param numDiv
	 * @param dividendosPorPagar
	 * @return
	 */
	private static EstadoDividendoPrestamo obtenerEstadoDividendo(Long numDiv,
			List<DividendoPrestamo> dividendosPorPagar) {

		for (DividendoPrestamo dividendo : dividendosPorPagar) {
			Long numDivTmp = dividendo.getDividendoPrestamoPk().getNumdiv();
			if (numDiv.compareTo(numDivTmp) == 0) {
				return dividendo.getEstadoDividendoPrestamo();
			}
		}
		return null;
	}

	/**
	 * Retorna el numero de dividendo siguiente (de acuerdo al enviado en el
	 * parametro) de los que estan registrados en la bdd
	 * 
	 * @param dividendosporPagar
	 * @param numeroDividendo
	 * @return
	 */
	@SuppressWarnings(value = "unchecked")
	private static Long obtenerSiguienteNumDividendo(List<DividendoPrestamo> dividendosporPagar,
			Long numeroDividendoActual) {

		for (Iterator iterator = dividendosporPagar.iterator(); iterator.hasNext();) {
			DividendoPrestamo dividendoPrestamo = (DividendoPrestamo) iterator.next();

			if (dividendoPrestamo.getDividendoPrestamoPk().getNumdiv().compareTo(numeroDividendoActual) == 0) {
				Long dividendoPrestamoSiquiente = ((DividendoPrestamo) iterator.next()).getDividendoPrestamoPk()
						.getNumdiv();
				return dividendoPrestamoSiquiente;
			}
		}

		log.error("NO encontro dividendo!");

		return null;
	}
}