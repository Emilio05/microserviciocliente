package ce.pucmm.microserviciocliente.Model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Cliente")
@Table(name = "cliente")
@Where(clause = "deleted = 0")

public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "email")
    private String email;

    @Column(name = "usuario_id")
    private String usuario_id;



    private boolean deleted = false;


    public Cliente() {

    }

    public Cliente(String nombre, String direccion, String email, String usuario_id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.usuario_id = usuario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario_id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
