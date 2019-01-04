package ce.pucmm.microserviciocliente.Model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "Transaccion")
@Table(name = "transaccion")
@Where(clause = "deleted = 0")
public class Transaccion implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "transaccion_id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "metodopago")
    private String metodoPago;

    @Column(name = "monto")
    private double monto;

    @Column(name = "devuelta")
    private double devuelta;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "factura_id", nullable = true, updatable = false)
    private Factura factura;

    private boolean deleted = false;

    public Transaccion(){

    }

    public Transaccion(LocalDate fecha, String metodoPago, double monto, double devuelta, Factura factura) {
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.devuelta = devuelta;
        this.factura = factura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(double devuelta) {
        this.devuelta = devuelta;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
