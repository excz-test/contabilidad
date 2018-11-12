/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import clases.Auditoria;
import clases.Catalogo;
import clases.Empresa;
import clases.Funcionalidad;
import clases.ItemCatalogo;
import clases.Modulo;
import clases.ModuloRol;
import clases.Rol;
import clases.RolFuncionalidad;
import clases.Usuario;
import clases.UsuarioRol;
import java.time.LocalTime;
import java.util.ArrayList;
import servicios.CargaParametros;
import utilidades.ManejoArchivos;

/**
 *
 * @author excz010715
 */
public class Dev {

    public static void main(String[] args) {
        ManejoArchivos<Usuario> manejoArchivos = new ManejoArchivos(Usuario.class);
       manejoArchivos.registrar(new Usuario(1, "Eduardo", "Conrado", "edu", "edu"));
        manejoArchivos.registrar(new Usuario(2, "Administrador", null, "admin", "admin"));
        manejoArchivos.registrar(new Usuario(3, "Megan", "Fox", "s", "s"));
        /*
        ArrayList<Usuario> listadoUsuarios= new ArrayList<>();
        listadoUsuarios = manejoArchivos.obtenerListado();
            
        for (Usuario usuario : listadoUsuarios) {
            System.out.println(usuario.toString());
        }*/
        
        ManejoArchivos<Empresa> manejoArchivosEmpresa = new ManejoArchivos<>(Empresa.class);
        
        manejoArchivosEmpresa.registrar(new Empresa(1, "Priscila Studio", "1721106738001", "024529194", "Quito"));
        manejoArchivosEmpresa.registrar(new Empresa(2, "Eduardo Conrado", "0502863871001", "0995000585", "Latacunga"));
        
        ArrayList<Empresa> listadoEmpresas = new ArrayList<>();
        
        listadoEmpresas = manejoArchivosEmpresa.obtenerListado();
        for (Empresa empresa : listadoEmpresas) {
            System.out.println(empresa.toString());
        }
        
        CargaParametros cargaParametros = new CargaParametros();
        ArrayList<Usuario> listadoUsuarios = new ArrayList<>();
        listadoUsuarios = cargaParametros.cargarUsuarios();
        for (Usuario usuario : listadoUsuarios) {
            System.out.println("---->"+usuario.toString());
        }
        
        ManejoArchivos<Auditoria> manejoArchivosAuditoria = new ManejoArchivos<>(Auditoria.class);
        manejoArchivosAuditoria.registrar(new Auditoria(1, "Auditando", "ACC", LocalTime.now(), new Usuario()));
        manejoArchivosAuditoria.registrar(new Auditoria(2, "Auditando 2", "DEL", LocalTime.now(), new Usuario()));
        
        ManejoArchivos<Catalogo> manejoArchivosCatalogo = new ManejoArchivos<>(Catalogo.class);
        manejoArchivosCatalogo.registrar(new Catalogo(1, "PRO", "Provincias", "Provincias del Ecuador", "ACT"));
        manejoArchivosCatalogo.registrar(new Catalogo(2, "EST", "Estado", "Estados", "ACT"));
        manejoArchivosCatalogo.registrar(new Catalogo(3, "CUE", "Cuentas", "Tipos de cuentas", "ACT"));
        manejoArchivosCatalogo.registrar(new Catalogo(4, "FDP", "FormaPago", "Formas de pago", "ACT"));
        
        ManejoArchivos<ItemCatalogo> manejoArchivosItemCatalogo = new ManejoArchivos<>(ItemCatalogo.class);
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(1, "PRO", "Azuay", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(2, "PRO", "Bolivar", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(3, "EST", "Activo", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(4, "EST", "Inactivo", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(5, "CUE", "Ahorro", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(6, "CUE", "Corriente", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(7, "FDP", "Efectivo", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(8, "FDP", "Depósito", null, "ACT"));
        manejoArchivosItemCatalogo.registrar(new ItemCatalogo(9, "FDP", "Tarjeta de crédito", null, "ACT"));
        
        /**
         * Funcionalidad
         */
        ManejoArchivos<Funcionalidad> manejoArchivosFuncionalidad = new ManejoArchivos<>(Funcionalidad.class);
        manejoArchivosFuncionalidad.registrar(new Funcionalidad(1, "ACC", "Acceso", "Acceso a módulo", "ACT"));
        manejoArchivosFuncionalidad.registrar(new Funcionalidad(2, "OPE", "Operación", "Operador de módulo", "ACT"));
        manejoArchivosFuncionalidad.registrar(new Funcionalidad(3, "VIS", "Visualización", "Vizualización de módulo", "ACT"));
        
        /**
         * RolFuncionalidad
         * new RolFuncionalidad(id, idRol, idFuncionalidad)
         */
        ManejoArchivos<RolFuncionalidad> manejoArchivoRolFun = new ManejoArchivos<>(RolFuncionalidad.class);
        manejoArchivoRolFun.registrar(new RolFuncionalidad(1, 2, 1));
        manejoArchivoRolFun.registrar(new RolFuncionalidad(2, 2, 2));
        manejoArchivoRolFun.registrar(new RolFuncionalidad(3, 2, 3));
        manejoArchivoRolFun.registrar(new RolFuncionalidad(4, 3, 2));
        
        /**
         * Modulo
         */
        ManejoArchivos<Modulo> manejoArchivosModulo = new ManejoArchivos<>(Modulo.class);
        manejoArchivosModulo.registrar(new Modulo(1, "INV", "Inventario", "ACT"));
        manejoArchivosModulo.registrar(new Modulo(2, "PAG", "Pagos", "ACT"));
        manejoArchivosModulo.registrar(new Modulo(3, "VEN", "Ventas", "ACT"));
        manejoArchivosModulo.registrar(new Modulo(4, "REP", "Reportes", "ACT"));
        manejoArchivosModulo.registrar(new Modulo(5, "LIB", "Libro diario", "ACT"));
        manejoArchivosModulo.registrar(new Modulo(6, "ADM", "Administración", "ACT"));

        /**
         * Rol
         */
        ManejoArchivos<Rol> manejoArchivosRol = new ManejoArchivos<>(Rol.class);
        manejoArchivosRol.registrar(new Rol(1, "ADR", "Administrador", "Control completo del sistema", "ACT"));
        manejoArchivosRol.registrar(new Rol(2, "GER", "Gerente", "Gerente del sistema", "ACT"));
        manejoArchivosRol.registrar(new Rol(3, "SEC", "Secretaria", "Secretaria institucional", "ACT"));
        manejoArchivosRol.registrar(new Rol(4, "BOD", "Bodeguero", "Rol de bodeguero", "ACT"));
        manejoArchivosRol.registrar(new Rol(5, "DES", "Despachador", "Rol de despacho", "ACT"));
        manejoArchivosRol.registrar(new Rol(6, "UBA", "Usuario básico", "Rol de usuario básico", "INA"));
        manejoArchivosRol.registrar(new Rol(7, "UAV", "Usuario avanzado", "Rol de usuario avanzado", "INA"));
        
        /**
         * Modulo-Rol
         * new ModuloRol(id, idModulo, idRol)
         */ 
        ManejoArchivos<ModuloRol> manejoModRol = new ManejoArchivos<>(ModuloRol.class);
        manejoModRol.registrar(new ModuloRol(1, 6, 1));
        manejoModRol.registrar(new ModuloRol(2, 1, 2));
        manejoModRol.registrar(new ModuloRol(3, 2, 2));
        manejoModRol.registrar(new ModuloRol(4, 3, 2));
        manejoModRol.registrar(new ModuloRol(5, 4, 2));
        manejoModRol.registrar(new ModuloRol(6, 5, 2));
        manejoModRol.registrar(new ModuloRol(7, 2, 3));
        manejoModRol.registrar(new ModuloRol(8, 3, 3));
        manejoModRol.registrar(new ModuloRol(9, 4, 3));
        
        /**
         * Usuario-Rol
         * new Usuario(id, idUsuario, idRol)
         */
        ManejoArchivos<UsuarioRol> manejoArchivosUsuRol = new ManejoArchivos<>(UsuarioRol.class);
        manejoArchivosUsuRol.registrar(new UsuarioRol(1, 1, 1));
        manejoArchivosUsuRol.registrar(new UsuarioRol(2, 2, 2));
        manejoArchivosUsuRol.registrar(new UsuarioRol(3, 3, 3));
        
        /**
         * Modulo-rol
         * new ModuloRol(id, idModulo, idRol)
         */ 
        ManejoArchivos<ModuloRol> manejoArchivosModRol = new ManejoArchivos<>(ModuloRol.class);
        manejoArchivosModRol.registrar(new ModuloRol(1, 1, 1));
        manejoArchivosModRol.registrar(new ModuloRol(2, 2, 1));
        manejoArchivosModRol.registrar(new ModuloRol(3, 3, 1));
        manejoArchivosModRol.registrar(new ModuloRol(4, 4, 1));
        manejoArchivosModRol.registrar(new ModuloRol(5, 5, 1));
        manejoArchivosModRol.registrar(new ModuloRol(6, 6, 1));
        manejoArchivosModRol.registrar(new ModuloRol(7, 2, 2));
        manejoArchivosModRol.registrar(new ModuloRol(8, 3, 2));
        manejoArchivosModRol.registrar(new ModuloRol(9, 4, 2));
        manejoArchivosModRol.registrar(new ModuloRol(10, 5, 2));
        manejoArchivosModRol.registrar(new ModuloRol(11, 1, 2));
        manejoArchivosModRol.registrar(new ModuloRol(12, 3, 3));
        manejoArchivosModRol.registrar(new ModuloRol(13, 4, 3));
        manejoArchivosModRol.registrar(new ModuloRol(14, 1, 3));
    }
}
