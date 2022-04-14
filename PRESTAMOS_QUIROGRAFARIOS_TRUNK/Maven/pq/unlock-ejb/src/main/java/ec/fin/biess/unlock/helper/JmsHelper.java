/*
 * Copyright 2013 BIESS - ECUADOR
 * 
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.unlock.helper;

import javax.ejb.Local;

/**
 * Interface que expone el servicio de envio de mensajes JMS.
 * 
 * @author Omar Villanueva
 * @version 1.0
 *
 */
@Local
public interface JmsHelper {

	/**
	 * Método que envia mensajes JMS.
	 * 
	 * @param mensaje
	 * @param usuario
	 * @param emails
	 * @param titulo
	 */
	public void sendMessageJms(String mensaje, String usuario,String emails,String titulo) ;

}
