package ec.gov.iess.afiliadocore.test;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class UnitTest
{
  private static final String URL = "jnp://localhost:1099";
  private static final String FACTORY = "org.jnp.interfaces.NamingContextFactory";
  private static final String PKG_PREFIXES = "org.jboss.naming:org.jnp.interface";
  
  public static <T> T getServiceBean(String jndiName)
  {
    T serviceLocal = null;
    
    Properties env = new Properties();
    env.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
    env.put("java.naming.provider.url", "jnp://localhost:1099");
    env.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interface");
    try
    {
      InitialContext ic = new InitialContext(env);
      serviceLocal = (T) ic.lookup(jndiName);
    }
    catch (NamingException ex)
    {
      ex.printStackTrace();
    }
    return serviceLocal;
  }
}
