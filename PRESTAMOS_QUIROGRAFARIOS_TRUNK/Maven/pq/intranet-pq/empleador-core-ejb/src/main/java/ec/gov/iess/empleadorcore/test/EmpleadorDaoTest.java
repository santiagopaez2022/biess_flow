package ec.gov.iess.empleadorcore.test;

import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.empleadorcore.dao.EmpleadorDao;
import ec.gov.iess.empleadorcore.modelo.persistencia.embedable.Empleador;
import java.io.PrintStream;

public class EmpleadorDaoTest
{
  private static final String JNDINAME = "EmpleadorDaoJpa/remote";
  
  public void testFindByPk()
  {
    EmpleadorDao empleadorDao = (EmpleadorDao)UnitTest.getServiceBean("EmpleadorDaoJpa/remote");
    try
    {
      Empleador res = (Empleador)empleadorDao.findByPk("1802180610001");
      System.out.println("EMPLEADOR == " + res.getCodtipemp());
    }
    catch (EntidadNoExisteException e)
    {
      e.printStackTrace();
    }
  }
}
