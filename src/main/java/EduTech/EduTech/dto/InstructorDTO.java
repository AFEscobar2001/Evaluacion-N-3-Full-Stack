package EduTech.EduTech.dto;

import java.util.List;

import EduTech.EduTech.model.Curso;
import EduTech.EduTech.model.Instructor;

public class InstructorDTO {

    private String rut;
    private String nombre;
    private String apellido;
    private String correo;
    private List<String> cursos;

    public InstructorDTO() {
        
    }

    public InstructorDTO(Instructor instructor) {
        this.rut = instructor.getRut();
        this.nombre = instructor.getNombre();
        this.apellido = instructor.getApellido();
        this.correo = instructor.getCorreo();

        if (instructor.getCursos() != null) {
            this.cursos = instructor.getCursos()
                                    .stream()
                                    .map(Curso::getNombre)
                                    .toList();
        } else {
            this.cursos = List.of();
        }
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public List<String> getCursos() {
        return cursos;
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

}
