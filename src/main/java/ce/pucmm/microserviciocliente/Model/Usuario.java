package ce.pucmm.microserviciocliente.Model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Where(clause = "deleted = 0")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "rol_id", nullable = true, updatable = false)
    private String rol_id;

    private boolean deleted = false;

    public Usuario() {

    }

    public Usuario(String username, String password, String email, String rol_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol_id = rol_id;
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

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
