package ce.pucmm.microserviciocliente.Model;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Where(clause = "deleted = 0")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private int id;

    @NotEmpty(message="El usuario no puede estar vacio")
    @Column(name = "username")
    private String username;

    @NotEmpty(message="La contrasena no puede estar vacia")
    @Column(name = "password")
    private String password;

    @NotEmpty(message="El correo no puede estar vacio")
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "rol_id", nullable = true, updatable = false)
    private Rol rol;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval =  true)
    private Cliente cliente;

    private boolean deleted = false;

    public Usuario(){

    }
    public Usuario(String username, String password, String email, Rol rol, Cliente cliente) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.cliente = cliente;
        this.active = 1;
    }


    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
