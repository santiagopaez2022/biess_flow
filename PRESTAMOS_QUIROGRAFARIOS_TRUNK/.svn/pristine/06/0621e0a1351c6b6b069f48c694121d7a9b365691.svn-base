/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.biess.unlock.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author santiago.castillo
 */
public class TemplateTest {

    @Test
    @Ignore
    public void existeTemplate() throws IOException {
        String templatePath = "ec/fin/biess/unlock/templates/email/unlockaccount.ftl";
        Configuration cfg = new Configuration();
        /* Cargar el template */
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        Template template = cfg.getTemplate(templatePath);
        Assert.assertNotNull(template);
    }

    @Test
    @Ignore
    public void encuentraPropiedades() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ec.fin.biess.unlock.resources.mensajes");
        Enumeration<String> llaves = resourceBundle.getKeys();
        while (llaves.hasMoreElements()) {
            String key = llaves.nextElement();
            String valor = resourceBundle.getString(key);
            Assert.assertNotNull(key);
        }
    }
}
