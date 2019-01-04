package ce.pucmm.microserviciocliente.Repository;

import ce.pucmm.microserviciocliente.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombreRol(String rol);

}
