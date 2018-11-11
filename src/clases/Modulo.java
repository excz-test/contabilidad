/*
 * Clase Modulo
 */

package clases;

import java.io.Serializable;

/**
 *
 * @author excz010715
 */
public class Modulo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;
    private String descripcion;
    private String estado;

    public Modulo() {
    }

    public Modulo(Integer id, String nombre, String descripcion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Modulo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }

}
