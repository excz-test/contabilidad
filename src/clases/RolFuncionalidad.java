/*
 * Clase RolFuncionalidad
 */

package clases;

import java.io.Serializable;

/**
 *
 * @author excz010715
 */
public class RolFuncionalidad implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idRol;
    private Integer idFuncionalidad;

    public RolFuncionalidad(Integer id, Integer idRol, Integer idFuncionalidad) {
        this.id = id;
        this.idRol = idRol;
        this.idFuncionalidad = idFuncionalidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdFuncionalidad() {
        return idFuncionalidad;
    }

    public void setIdFuncionalidad(Integer idFuncionalidad) {
        this.idFuncionalidad = idFuncionalidad;
    }

    @Override
    public String toString() {
        return "RolFuncionalidad{" + "id=" + id + ", idRol=" + idRol + ", idFuncionalidad=" + idFuncionalidad + '}';
    }
    
}
