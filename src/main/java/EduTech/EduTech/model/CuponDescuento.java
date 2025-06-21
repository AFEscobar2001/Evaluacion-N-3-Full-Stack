package EduTech.EduTech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CuponDescuento {
    @Id
    private String codigo;
    
    private double descuento;
    private boolean activo;

    public CuponDescuento() {
        this.codigo = "";
        this.descuento = 0;
        this.activo = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
