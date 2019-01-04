package ce.pucmm.microserviciocliente.Service;


import ce.pucmm.microserviciocliente.Model.Rol;
import ce.pucmm.microserviciocliente.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("rolService")
@Transactional
public class RolServiceImpl {


    @Autowired
    private RolRepository rolRepository;

    public void crearRol(Rol rol) {
        rolRepository.save(rol);
    }

    public void actualizarRol(Rol rol) {
        crearRol(rol);
    }

    public void borrarRolPorId(long id) {
        rolRepository.deleteById(id);
    }

    public void borrarTodosLosRoles() {
        rolRepository.deleteAll();
    }

    public List<Rol> buscarTodosRoles() {
        return rolRepository.findAll();
    }

    public Rol buscarPorId(long id) {
        return rolRepository.findById(id).get();
    }

    public Rol findByNombreRol(String nombreRol) {
        return rolRepository.findByNombreRol(nombreRol);
    }

    public boolean rolExiste(Rol rol) {
        return findByNombreRol(rol.getNombreRol()) != null;
    }
}
