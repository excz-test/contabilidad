/*
 * Clase ServicioLogin
 */

package servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author excz010715
 */
public class ServicioLogin {
    
    Properties properties;

    public ServicioLogin() {
        properties = new Properties();
    }
    
    public void cargarProperties() {
        try (InputStream propertiesStream = ClassLoader.getSystemResourceAsStream("servicios/config.properties")) {
            properties.load(propertiesStream);
            System.out.println("propiedad 1: "+ properties.getProperty("user"));
        } catch (IOException ex) {
            Logger.getLogger(ServicioLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(String username, String password) {
        cargarProperties();
        return true;
    }
    
    

}
