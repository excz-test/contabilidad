/*
 * Clase Notificaicones
 */

package utilidades;

import javax.swing.JOptionPane;

/**
 *
 * @author excz010715
 */
public final class Notificaciones {

    public static void mensajeInformativo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static boolean mensajeConfirmacion(String titulo, String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(null, mensaje);
        return opcion == 0;
        
    }
    
    public static String inputDialog(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }
    
    
}
