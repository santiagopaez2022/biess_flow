package ec.gov.iess.afiliadocore.test;

import ec.gov.iess.afiliadocore.dao.AfiliadoDao;
import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.Afiliado;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import java.io.PrintStream;

public class AfiliadoDaoTest
{
  private static final String JNDINAME = "AfiliadoDaoJpa/remote";
  
  public void testFindByPk()
  {
    AfiliadoDao afiliadoDao = (AfiliadoDao)UnitTest.getServiceBean("AfiliadoDaoJpa/remote");
    try
    {
      Afiliado res = (Afiliado)afiliadoDao.findByPk("1802747277");
      System.out.println("AFILIADO CONSULTADO == " + res.getRucemp());
    }
    catch (EntidadNoExisteException e)
    {
      e.printStackTrace();
    }
  }
}
