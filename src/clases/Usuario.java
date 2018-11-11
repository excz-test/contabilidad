/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.io.Serializable;

/**
 *
 * @author excz010715
 */
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer codigo;
    private String nombres;
    private String apellidos;
    private String identificacion;
    private String telefono;
    private String username;
    private String password;
    private String correo;
    private String estado; //ina act

    public Usuario() {
    }
    
    public Usuario(Integer codigo, String nombres, String apellidos, String username, String password) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
    }

    public Usuario(Integer id, String nombres, String apellidos, String identificacion, String telefono, String username, String password, String correo, String estado) {
        this.codigo = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", identificacion=" + identificacion + ", telefono=" + telefono + ", username=" + username + ", password=" + password + ", correo=" + correo + ", estado=" + estado + '}';
    }
    
}
