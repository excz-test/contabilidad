/*
 * Clase Empresa
 */

package clases;

import java.io.Serializable;

/**
 *
 * @author excz010715
 */
public class Empresa implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String razonSocial;
    private String identificacion;
    private String direccion;
    private String personaContacto;
    private String telefono;
    private String ciudad;
    private String provincia;
    private String canton;
    private String estado;

    public Empresa() {
    }

    public Empresa(Integer id, String razonSocial, String identificacion, String telefono, String ciudad) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public Empresa(Integer id, String razonSocial, String identificacion, String direccion, String personaContacto, String telefono, String ciudad, String provincia, String canton, String estado) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.personaContacto = personaContacto;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.canton = canton;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", razonSocial=" + razonSocial + ", identificacion=" + identificacion + ", direccion=" + direccion + ", personaContacto=" + personaContacto + ", telefono=" + telefono + ", ciudad=" + ciudad + ", provincia=" + provincia + ", canton=" + canton + ", estado=" + estado + '}';
    }

}
