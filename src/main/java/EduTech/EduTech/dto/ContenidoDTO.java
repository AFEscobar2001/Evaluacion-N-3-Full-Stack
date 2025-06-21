package EduTech.EduTech.dto;

import EduTech.EduTech.model.Contenido;

public class ContenidoDTO {

    private int id;
    private String titulo;
    private String descripcion;
    private String nombreCurso;
    private String nombreProveedor;

    public ContenidoDTO(Contenido contenido) {
        this.id = contenido.getId();
        this.titulo = contenido.getTitulo();
        this.descripcion = contenido.getDescripcion();

        if (contenido.getCurso() != null) {
            this.nombreCurso = contenido.getCurso().getNombre();
        } else {
            this.nombreCurso = "Sin curso";
        }

        if (contenido.getProveedor() != null) {
            this.nombreProveedor = contenido.getProveedor().getNombre();
        } else {
            this.nombreProveedor = "Sin proveedor";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
    
}
