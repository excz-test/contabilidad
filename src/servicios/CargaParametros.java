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
public class CargaParametros<T> {

    ManejoArchivos<?> manejoArchivos;
    private final Class<T> entidad;
    
    public CargaParametros(final Class<T> entidad) {
        this.entidad = entidad;        
    }
    
    public ArrayList<?> cargarDatos() {
        manejoArchivos = new ManejoArchivos<>(entidad);
        return (ArrayList<?>) (T) manejoArchivos.obtenerListado();
    }
    
    
}
