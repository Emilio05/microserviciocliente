package ce.pucmm.microserviciocliente.Repository;

import ce.pucmm.microserviciocliente.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNombre(String nombre);


}
