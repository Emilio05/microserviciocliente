package ce.pucmm.microserviciocliente.Service;


import ce.pucmm.microserviciocliente.Model.Rol;

import java.util.List;

public interface RolService {

    void crearRol(Rol rol);

    void actualizarRol(Rol rol);

    void borrarRolPorId(long id);

    void borrarTodosLosRoles();

    List<Rol> buscarTodosRoles();

    Rol buscarPorId(long id);

    Rol findByNombreRol(String nombreRol);

    boolean rolExiste(Rol cliente);
}
