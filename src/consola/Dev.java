/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import clases.Empresa;
import clases.Usuario;
import java.util.ArrayList;
import utilidades.ManejoArchivos;

/**
 *
 * @author excz010715
 */
public class Dev {

    public static void main(String[] args) {
        ManejoArchivos<Usuario> manejoArchivos = new ManejoArchivos(Usuario.class);
/*
        manejoArchivos.registrar(new Usuario(1, "Eduardo", "Conrado", "edu", "edu"));
        manejoArchivos.registrar(new Usuario(2, "Administrador", null, "admin", "admin"));
        manejoArchivos.registrar(new Usuario(3, "Megan", "Fox", "s", "s"));
        */
        ArrayList<Usuario> listadoUsuarios= new ArrayList<>();
        listadoUsuarios = manejoArchivos.obtenerListado();
            
        for (Usuario usuario : listadoUsuarios) {
            System.out.println(usuario.toString());
        }
        
        ManejoArchivos<Empresa> manejoArchivosEmpresa = new ManejoArchivos<>(Empresa.class);
        /*
        manejoArchivosEmpresa.registrar(new Empresa(1, "Priscila Studio", "1721106738001", "024529194", "Quito"));
        manejoArchivosEmpresa.registrar(new Empresa(2, "Eduardo Conrado", "0502863871001", "0995000585", "Latacunga"));*/
        
        ArrayList<Empresa> listadoEmpresas = new ArrayList<>();
        
        listadoEmpresas = manejoArchivosEmpresa.obtenerListado();
        for (Empresa empresa : listadoEmpresas) {
            System.out.println(empresa.toString());
        }

    }
}
