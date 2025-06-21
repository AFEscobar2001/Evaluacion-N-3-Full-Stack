package EduTech.EduTech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titular;
    private String numero;       
    private String banco;
    private String vencimiento;

    @ManyToOne
    @JoinColumn(name = "usuario_correo")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "forma_pago_id")
    @JsonBackReference
    private FormaPago formaPago;

    public Tarjeta() {
        this.titular = "";
        this.numero = "";
        this.banco = "";
        this.vencimiento = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

}
