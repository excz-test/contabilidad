/*
 * Clase Auditoria
 */

package clases;

import java.io.Serializable;
import java.time.LocalTime;

/**
 *
 * @author excz010715
 */
public class Auditoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String texto;
    private String accion;
    private LocalTime fecha;
    private Usuario usuario;

    public Auditoria() {
    }

    public Auditoria(Integer id, String texto, String accion, LocalTime fecha, Usuario usuario) {
        this.id = id;
        this.texto = texto;
        this.accion = accion;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Auditoria{" + "id=" + id + ", texto=" + texto + ", accion=" + accion + ", fecha=" + fecha + ", usuario=" + usuario + '}';
    }
    
}
