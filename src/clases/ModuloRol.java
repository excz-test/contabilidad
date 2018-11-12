/*
 * Clase ModuloRol
 */

package clases;

import java.io.Serializable;

/**
 *
 * @author excz010715
 */
public class ModuloRol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idModulo;
    private Integer idRol;

    public ModuloRol() {
    }

    public ModuloRol(Integer id, Integer idModulo, Integer idRol) {
        this.id = id;
        this.idModulo = idModulo;
        this.idRol = idRol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "ModuloRol{" + "id=" + id + ", idModulo=" + idModulo + ", idRol=" + idRol + '}';
    }
    
}
