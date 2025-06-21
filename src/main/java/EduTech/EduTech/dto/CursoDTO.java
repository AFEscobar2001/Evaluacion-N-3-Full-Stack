package EduTech.EduTech.dto;

import java.util.List;

import EduTech.EduTech.model.Curso;

    public class CursoDTO {
        private int id;
        private String nombre;
        private Double precio;
        private List<String> nombresUsuarios;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nombre = curso.getNombre();
        this.precio = curso.getPrecio();

        this.nombresUsuarios = curso.getUsuarios().stream()
            .map(usuario -> usuario.getPersona() != null
                ? usuario.getPersona().getNombre() + " " + usuario.getPersona().getApellido()
                : "Sin nombre")
            .toList();
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

    public List<String> getNombresUsuarios() {
        return nombresUsuarios;
    }

    public void setNombresUsuarios(List<String> nombresUsuarios) {
        this.nombresUsuarios = nombresUsuarios;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
