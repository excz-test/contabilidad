/*
 * Consola 2
 */

package consola;

import clases.Catalogo;
import clases.Usuario;
import java.util.ArrayList;
import servicios.CargaParametros;

/**
 *
 * @author excz010715
 */
public class Consola {

    public static void main (String [] args) {
        
        ArrayList<Catalogo> listadoUsuarios= new ArrayList<>();
        CargaParametros<Catalogo> paramUsu = new CargaParametros<>(Catalogo.class);
        listadoUsuarios = (ArrayList<Catalogo>) paramUsu.cargarDatos();
        for (Catalogo usuario : listadoUsuarios) {
            System.out.println("---->"+usuario.toString());
        }
    }
}
