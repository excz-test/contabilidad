/*
 * Clase UsuarioRol
 */

package clases;

import java.io.Serializable;

/**
 *
 * @author excz010715
 */
public class UsuarioRol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idUsuario;
    private Integer idRol;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer id, Integer idUsuario, Integer idRol) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "UsuarioRol{" + "id=" + id + ", idUsuario=" + idUsuario + ", idRol=" + idRol + '}';
    }
    
    

}
