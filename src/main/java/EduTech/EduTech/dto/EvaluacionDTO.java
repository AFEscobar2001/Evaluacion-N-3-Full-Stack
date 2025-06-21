package EduTech.EduTech.dto;

import EduTech.EduTech.model.Evaluacion;

public class EvaluacionDTO {
    private int id;
    private double nota;
    private String observacion;
    private String usuarioCorreo;
    private String contenidoTitulo;

    public EvaluacionDTO(Evaluacion evaluacion) {
        this.id = evaluacion.getId();
        this.nota = evaluacion.getNota();
        this.observacion = evaluacion.getObservacion();
        this.usuarioCorreo = evaluacion.getUsuario() != null ? evaluacion.getUsuario().getCorreo() : null;
        this.contenidoTitulo = evaluacion.getContenido() != null ? evaluacion.getContenido().getTitulo() : null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getContenidoTitulo() {
        return contenidoTitulo;
    }

    public void setContenidoTitulo(String contenidoTitulo) {
        this.contenidoTitulo = contenidoTitulo;
    }
    
}
