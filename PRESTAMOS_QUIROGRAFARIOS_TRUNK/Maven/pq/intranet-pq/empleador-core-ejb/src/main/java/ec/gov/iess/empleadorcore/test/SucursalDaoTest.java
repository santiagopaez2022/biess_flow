package ec.gov.iess.empleadorcore.test;

import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.empleadorcore.dao.SucursalDao;
import ec.gov.iess.empleadorcore.modelo.persistencia.embedable.Sucursal;
import ec.gov.iess.empleadorcore.modelo.persistencia.pk.SucursalPK;
import java.io.PrintStream;

public class SucursalDaoTest
{
  private static final String JNDINAME = "SucursalDaoJpa/remote";
  
  public void testFindByPk()
  {
    SucursalDao sucursalDao = (SucursalDao)UnitTest.getServiceBean("SucursalDaoJpa/remote");
    try
    {
      Sucursal res = (Sucursal)sucursalDao.findByPk(new SucursalPK("0001", "1802180610001"));
      System.out.println("SUCURSAL == " + res.getApenomrepleg());
    }
    catch (EntidadNoExisteException e)
    {
      e.printStackTrace();
    }
  }
}
