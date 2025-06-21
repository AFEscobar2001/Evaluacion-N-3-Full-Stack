package EduTech.EduTech.dto;

import EduTech.EduTech.model.Incidencia;

public class IncidenciaDTO {
    private int id;
    private String detalle;
    private String fecha;
    private String usuarioCorreo;

    public IncidenciaDTO(Incidencia incidencia) {
        this.id = incidencia.getId();
        this.detalle = incidencia.getDetalle();
        this.fecha = incidencia.getFecha();
        this.usuarioCorreo = incidencia.getUsuario() != null ? incidencia.getUsuario().getCorreo() : null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

}
