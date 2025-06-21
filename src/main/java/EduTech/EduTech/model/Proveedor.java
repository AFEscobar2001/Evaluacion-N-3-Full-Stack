package EduTech.EduTech.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int telefono;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Contenido> contenidos;

    public Proveedor() {
        this.nombre = "";
        this.telefono = 0;
    }

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

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
