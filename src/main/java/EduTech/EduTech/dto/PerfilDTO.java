package EduTech.EduTech.dto;

import EduTech.EduTech.model.Perfil;

public class PerfilDTO {

    private int id;
    private String nombre;

    public PerfilDTO() {
        
    }

    public PerfilDTO(Perfil perfil) {
        this.id = perfil.getId();
        this.nombre = perfil.getNombre();
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

}
