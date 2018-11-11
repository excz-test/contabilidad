/*
 * Clase ItemCatalogo
 */

package clases;

import java.io.Serializable;

/**
 *
 * @author excz010715
 */
public class ItemCatalogo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String estado;

    public ItemCatalogo() {
    }

    public ItemCatalogo(Integer id, String codigo, String nombre, String descripcion, String estado) {
        this.id = id;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        return "ItemCatalogo{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
    
}
