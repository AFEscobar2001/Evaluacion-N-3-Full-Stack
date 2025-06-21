package EduTech.EduTech.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private Double precio;

    @ManyToMany(mappedBy = "cursos")
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "cursos")
    private List<Instructor> instructores;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contenido> contenidos;

    public Curso() {
        this.nombre = "";
        this.precio = 0.0;    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Instructor> getInstructores() {
        return instructores;
    }

    public void setInstructores(List<Instructor> instructores) {
        this.instructores = instructores;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
