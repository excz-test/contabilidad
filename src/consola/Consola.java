/*
 * Consola 2
 */

package consola;

import clases.Catalogo;
import clases.Usuario;
import java.util.ArrayList;
import servicios.CargaParametros;
import utilidades.ManejoArchivos;

/**
 *
 * @author excz010715
 */
public class Consola {

    public static void main (String [] args) {
        
        /*ArrayList<Catalogo> listadoUsuarios= new ArrayList<>();
        CargaParametros<Catalogo> paramUsu = new CargaParametros<>(Catalogo.class);
        listadoUsuarios = (ArrayList<Catalogo>) paramUsu.cargarDatos();
        for (Catalogo usuario : listadoUsuarios) {
            System.out.println("---->"+usuario.toString());
        }*/
         ManejoArchivos<Catalogo> manejoArchivosCatalogo = new ManejoArchivos<>(Catalogo.class);
        manejoArchivosCatalogo.registrar(new Catalogo(0, "DEF", "Default", null, "INA"));
         manejoArchivosCatalogo.registrar(new Catalogo(1, "PRO", "Provincias", "Provincias del Ecuador", "INA"));
        manejoArchivosCatalogo.registrar(new Catalogo(2, "EST", "Estado", "Estados", "ACT"));
        manejoArchivosCatalogo.registrar(new Catalogo(3, "CUE", "Cuentas", "Tipos de cuentas", "ACT"));
        manejoArchivosCatalogo.registrar(new Catalogo(4, "FDP", "FormaPago", "Formas de pago", "ACT"));
    }
}
