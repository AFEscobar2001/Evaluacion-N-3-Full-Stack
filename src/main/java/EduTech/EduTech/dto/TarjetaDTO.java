package EduTech.EduTech.dto;

import EduTech.EduTech.model.Tarjeta;

public class TarjetaDTO {
    private int id;
    private String titular;
    private String banco;
    private String vencimiento;
    private String formaPago;

    public TarjetaDTO() {
        
    }

    public TarjetaDTO(Tarjeta tarjeta) {
        this.id = tarjeta.getId();
        this.titular = tarjeta.getTitular();
        this.banco = tarjeta.getBanco();
        this.vencimiento = tarjeta.getVencimiento();
        this.formaPago = (tarjeta.getFormaPago() != null) ? tarjeta.getFormaPago().getNombre() : null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

}
