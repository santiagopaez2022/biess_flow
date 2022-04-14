/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.planillaspq.web.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import ec.gov.iess.seguridades.modelo.Menu;


/**
 * Incluir aqui­ la descripcion de la clase.
 * 
 * @version $Revision: 1.1 $ [04/10/2008]
 * @author caldaz
 */

public class ContainerBean extends BaseBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reportDS = super.getResource("recursos","report.datasource");
	private String cedula;
	private Menu menu;
	private final String LOGOUT = "logout";
	
	public ContainerBean() {
		menu = (Menu) super.getHttpServletRequest().getSession(true)
		.getAttribute("menu");
		cedula = super.getHttpServletRequest().getRemoteUser();
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String logoutAction() {
		super.getHttpServletRequest().getSession(true).invalidate();
		return LOGOUT;
	}

	public Connection getConnection()
	throws NamingException, SQLException
	{
		 DataSource dataSource = 
	            ( DataSource ) getContext().lookup( reportDS );

	        return dataSource.getConnection();
	}

	public static Context getContext()
	throws NamingException
	{
		return new InitialContext();
	}
}
