/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.MigracionPrestamoHost;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;

/**
 * 
 * Interface para la validacion de condiciones de creditos actuales, como
 * creditos vigentes, dividendos en mora, creditos con la misma cuenta bancaria,
 * creditos vigentes del host.
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Remote
public interface VerificacionCreditoServicioRemoto {

	/**
	 * Consulta si tiene un dividendos en mora en HL.
	 * 
	 * @param cedula
	 *            identificcaion de la persona
	 * @return true en caso de que tenga dividendos y false en caso de no
	 *         encontrar dividendos en mora.
	 * @author cvillarreal
	 */
	public boolean tienePrestamoMoraHl(String cedula,List<String> estadoMigrado);

	/**
	 * Consulta los dividendos en mora de los prestamos quirografarios vigentes
	 * en HL.
	 * 
	 * @param cedula
	 *            identificacion de la persona.
	 * @return una lista con los dividendos en mora si no tiene dividentos
	 *         retorn una lista vacia
	 * @author cvillarreal
	 */
	public List<DividendoPrestamo> listaPrestamoMoraHl(String cedula,List<String> estadoDividendo);

	/**
	 * Consulta si tiene prestamos vigentes de quirografarios en HL.
	 * 
	 * @param cedula
	 *            identificacion de la persona
	 * @return true en caso de que tenga prestamos vigentes y false en caso de
	 *         que no
	 * @author cvillarreal
	 */
	public boolean tienePrestamoVigentesHl(String cedula,List<String> estadoCredito);

	/**
	 * Consulta la lista de prestamos vigentes HL.
	 * 
	 * @param cedula
	 *            identificacion para la consulta de los prestamos
	 * @return una lista de clases de modelo {@link Prestamo} caso contrario una
	 *         lista vacia
	 * @see Prestamo
	 * @author cvillarreal
	 */
	public List<Prestamo> listaPrestamoVigentesHl(String cedula,List<String> estadoCredito);

	/**
	 * Verifica si existe un credito con la misma cuenta bancaria.
	 * 
	 * @param rucInstitucionFinanciera
	 *            run de la institucion financiera
	 * @param idTipoCuenta
	 *            codigo del tipo de cuenta
	 * @param numeroCuenta
	 *            numero de la ceunta bancaria
	 * @return true en caso de que exista un credito vigente caso contrario
	 *         false
	 * @author cvillarreal
	 */
	public boolean existePrestamoConLaMismaCuenta(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta,List<String> estadoCredito);

	/**
	 * Consulta la lista de prestamos que tengan la misma cuenta bancaria.
	 * 
	 * @param rucInstitucionFinanciera
	 *            run de la institucion financiera
	 * @param idTipoCuenta
	 *            codigo del tipo de cuenta
	 * @param numeroCuenta
	 *            numero de la ceunta bancaria
	 * @return unalista de clases de modelo {@link Prestamo} ca so contrario una
	 *         lista vacia
	 * @author cvillarreal
	 */
	public List<Prestamo> consultarPrestamosConLaMismaCuenta(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta,List<String> estadoCredito);

	/**
	 * Consulta una lista de prestamos vigentes en el Host
	 * 
	 * @param cedula
	 *            identificaciond e la persona
	 * 
	 * @return una lista de {@link MigracionPrestamoHost} caso contrario una
	 *         lista vacia
	 * @author cvillarreal
	 */
	public List<MigracionPrestamoHost> listaPrestamoVigentesHost(String cedula,List<String> estadoMigrado);

	/**
	 * Consulta si tiene creditos en mora del Host.
	 * 
	 * @param cedula
	 *            identificacin de la persona
	 * @return true en caso de que tenga creditos pendientes y falso en caso de
	 *         que no se encuentre la cedula en la tabla de migracion
	 * @author cvillarreal
	 */
	public boolean tienePrestamoVigentesHost(String cedula,List<String> estadoMigrado);

	

	/**
	 * consulta si tiene prestamos con la misma cuenta bancaria y cedula del solicitante
	 * @param rucInstitucionFinanciera
	 * @param idTipoCuenta
	 * @param numeroCuenta
	 * @param estadoCredito
	 * @param cedula
	 * @return
	 * @author jmolina
	 *
	 */
	public boolean existePrestamoConLaMismaCuentaYCedula(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta,List<String> estadoCredito,String cedula);
}
