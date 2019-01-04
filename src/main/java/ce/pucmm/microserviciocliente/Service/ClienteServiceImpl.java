package ce.pucmm.microserviciocliente.Service;



import ce.pucmm.microserviciocliente.Model.Cliente;
import ce.pucmm.microserviciocliente.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void crearCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void actualizarCliente(Cliente cliente) {
        crearCliente(cliente);
    }

    public void borrarClientePorId(Cliente cliente) {
        cliente.setDeleted(true);
        actualizarCliente(cliente);
    }

    public void borrarTodosLosClientes() {
        clienteRepository.deleteAll();
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(long id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente findByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    public boolean clienteExiste(Cliente cliente) {
        return findByNombre(cliente.getNombre()) != null;
    }

}
