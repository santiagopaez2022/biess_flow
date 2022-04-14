/* 
* Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
* Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
* file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
* by applicable law or agreed to in writing, software distributed under the License is 
* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
* express or implied. See the License for the specific language governing permissions 
* and limitations under the License.
*/

package ec.gov.iess.seguridades.ws.model.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Util para la conexion
 * @author Chan
 * @version $Revision: 1.2 $
 */
public class Util {
    /** Creates a new instance of Util */
    public Util() {

    }

    private static final String URL = "jnp://localhost:1099";

    private static final String FACTORY = "org.jnp.interfaces.NamingContextFactory";

    private static final String PKG_PREFIXES = "org.jboss.naming:org.jnp.interface";

    /**
     * @param <T>
     * @param jndiName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getServiceBean(String jndiName) {
	T serviceLocal = null;
	Properties env = new Properties();
	env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
	env.put(Context.PROVIDER_URL, URL);
	env.put(Context.URL_PKG_PREFIXES, PKG_PREFIXES);
	InitialContext ic;
	try {
	    ic = new InitialContext();
	    serviceLocal = (T) ic.lookup("".concat(jndiName));
	} catch (NamingException ex) {
	    ex.printStackTrace();
	}
	return serviceLocal;
    }
}