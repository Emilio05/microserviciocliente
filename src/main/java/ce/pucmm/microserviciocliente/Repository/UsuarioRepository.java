package ce.pucmm.microserviciocliente.Repository;

import ce.pucmm.microserviciocliente.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    @Query(value = "SELECT * FROM usuario u inner join rol r on u.rol_id = r.id where r.nombre_rol = 'Almacen';", nativeQuery = true)
    List<Usuario> usuariosAlmacen();

}
