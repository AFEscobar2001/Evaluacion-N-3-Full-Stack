package EduTech.EduTech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {
    @Id
    private String correo;

    private String contrasena;
    private boolean estado;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Persona persona;

    @ManyToMany
    @JoinTable(
        name = "perfil_usuario",
        joinColumns = @JoinColumn(name = "usuario_correo"),
        inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfiles;

    @ManyToMany
    @JoinTable(
        name = "usuario_curso",
        joinColumns = @JoinColumn(name = "usuario_correo"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Incidencia> incidencias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarjeta> tarjetas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Evaluacion> evaluaciones;

    public Usuario() {
        this.correo = "";
        this.contrasena = "";
        this.estado = true;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    
}
