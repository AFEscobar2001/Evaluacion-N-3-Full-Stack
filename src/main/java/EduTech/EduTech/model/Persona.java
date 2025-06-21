package EduTech.EduTech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Persona {
    @Id
    private String rut;
    
    private String nombre;
    private String apellido;

    @OneToOne
    @JoinColumn(name = "usuario_correo", referencedColumnName = "correo")
    private Usuario usuario;

    public Persona() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
