package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "transaciones")
public class Transferencia {
    @Id
    @Column(name = "numero")
    @SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private int numero;

    @Column(name = "numerocuenta")
    private String numerocuenta;

    @Column(name ="tipo")
    private String tipo;

    @Column(name ="monto")
    private double monto;
    @Column(name = "fecha")
    private Date fecha;

    public Transferencia() {

    }

    public Transferencia(String numerocuenta, String tipo, double monto, Date fecha) {
        this.numerocuenta = numerocuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
