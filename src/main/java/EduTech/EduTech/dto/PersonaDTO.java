package EduTech.EduTech.dto;

public class PersonaDTO {

    private String rut;
    private String nombre;
    private String apellido;
    private String correo;

    public PersonaDTO() {
        this.rut = "";
        this.nombre = "";
        this.apellido = "";
        this.correo = "";
    }

    public PersonaDTO(String rut, String nombre, String apellido, String correo) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
