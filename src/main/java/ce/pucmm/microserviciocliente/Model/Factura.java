package ce.pucmm.microserviciocliente.Model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "Factura")
@Table(name = "factura")
@Where(clause = "deleted = 0")
public class Factura implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "factura_id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "total")
    private double total;

    @Column(name = "condicion")
    private String condicion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @OneToMany(mappedBy="factura")
    private Set<Transaccion> transacciones;


    private boolean deleted = false;

    public Factura(){

    }

    public Factura(LocalDate fecha, double total, String condicion, Cliente cliente, Set<Transaccion> transacciones) {
        this.fecha = fecha;
        this.total = total;
        this.condicion = condicion;
        this.cliente = cliente;
        this.transacciones = transacciones;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Set<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
