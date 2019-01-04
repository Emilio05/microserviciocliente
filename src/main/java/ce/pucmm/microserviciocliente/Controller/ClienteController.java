package ce.pucmm.microserviciocliente.Controller;



import ce.pucmm.microserviciocliente.Model.Cliente;
import ce.pucmm.microserviciocliente.Model.Rol;
import ce.pucmm.microserviciocliente.Model.Usuario;
import ce.pucmm.microserviciocliente.Service.ClienteServiceImpl;
import ce.pucmm.microserviciocliente.Service.RolServiceImpl;
import ce.pucmm.microserviciocliente.Service.UsuarioServiceImpl;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private RolServiceImpl rolService;

    @RequestMapping(value = "/")
    public String clientes(Model model) {
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteService.buscarTodosClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean loginPOST(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password
    ) {

        if(usuarioService.login(username, password))
            return true;
            else
                return false;
    }

    @RequestMapping("/todos")
    public List<Cliente> verTodosLosClientes() {

        return clienteService.buscarTodosClientes();
    }

    @PostMapping(value = "/crear/")
    public void crearCliente(@RequestParam("nombre") String nombre, @RequestParam("direccion") String direccion, @RequestParam("email") String email,
                               @RequestParam("username") String username, HttpServletResponse httpResponse) throws IOException {

        Cliente cliente = new Cliente();


        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setEmail(email);
        cliente.setUsuario(usuarioService.findByUsername(username));
        Usuario u = usuarioService.findByUsername(username);

        clienteService.crearCliente(cliente);

        String API_KEY = "eca1ad61d12248452578b2c4f1eda5963d6d94b5";
        Client client = new Client(API_KEY);
        try{
            client.sendMessage(

                    "20120994@topograpp.site",
                     ""+ cliente.getEmail(),
                    "Informacion de aceeso al sistema",
                    "Nombre de Usuario: " + u.getUsername() + "\n Contrasena: "+ u.getPassword() +"\n Enlace del sistema: topograpp.site ",
                    "<b>Hola Mundo desde SparkPost</b>");
        }
        catch (SparkPostException s){
            System.out.println("ERROR");
        }
        httpResponse.sendRedirect("http://localhost:8080/clientes/");
    }


    @PostMapping(value = "/eliminar/{id}")
    public void eliminarCliente(@PathVariable String id, HttpServletResponse httpResponse) throws IOException {
        clienteService.borrarClientePorId(clienteService.buscarPorId(Integer.parseInt(id)));

        httpResponse.sendRedirect("http://localhost:8080/clientes/");

    }


    @PostMapping("/modificar/")
    public void modificarCliente(@RequestParam("nombre2") String nombre, @RequestParam("direccion2") String direccion, @RequestParam("email2") String email,
                                 @RequestParam("username2") String username, @RequestParam("id2") String id, HttpServletResponse httpResponse) throws IOException{
        Cliente c = clienteService.buscarPorId(Long.parseLong(id));
        c.setDireccion(direccion);
        c.setEmail(email);
        c.setNombre(nombre);
        c.setUsuario(usuarioService.findByUsername(username));
        clienteService.actualizarCliente(c);

        httpResponse.sendRedirect("http://localhost:8080/clientes/");

    }

}
