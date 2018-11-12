/*
 * Clase CargaParametros
 */

package servicios;

import clases.Usuario;
import java.util.ArrayList;
import utilidades.ManejoArchivos;

/**
 *
 * @author excz010715
 */
public class CargaParametros {

    private ArrayList<Usuario> listadoUsuario;
    ManejoArchivos<?> manejoArchivos;
    
    public CargaParametros() {
        listadoUsuario = new ArrayList<>();
    }
    
    public ArrayList<Usuario> cargarUsuarios() {
        manejoArchivos = new ManejoArchivos<>(Usuario.class);
        return (ArrayList<Usuario>) manejoArchivos.obtenerListado();
    }
    
    
}
