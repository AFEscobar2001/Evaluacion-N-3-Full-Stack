package EduTech.EduTech.dto;

import java.util.List;

import EduTech.EduTech.model.Perfil;
import EduTech.EduTech.model.Usuario;

public class UsuarioDTO {

    private String correo;
    private boolean estado;
    private List<String> perfiles;

    public UsuarioDTO() {

    }

    public UsuarioDTO(Usuario usuario) {
        this.correo = usuario.getCorreo();
        this.estado = usuario.isEstado();
        this.perfiles = usuario.getPerfiles()
                               .stream()
                               .map(Perfil::getNombre)
                               .toList();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<String> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<String> perfiles) {
        this.perfiles = perfiles;
    }

}
