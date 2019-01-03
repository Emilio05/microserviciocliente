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

import java.util.ArrayList;
import java.util.List;

@RestController
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

    @RequestMapping("/todos")
    public List<Cliente> verTodosLosClientes() {
        String API_KEY = "eca1ad61d12248452578b2c4f1eda5963d6d94b5";
        Client client = new Client(API_KEY);
        try{
            client.sendMessage(
                    "20120994@ce.pumm.edu.do",
                    "emilio.rojas05@gmail.com",
                    "Informacion de acceso al sistema",
                    "Nombre de Usuario:  ",
                    "<b>Hola Mundo desde SparkPost</b>");
        }
        catch (SparkPostException s){
            System.out.println("ERROR");
        }
        return clienteService.buscarTodosClientes();
    }

    @PostMapping(value = "/")
    public String crearCliente(@RequestParam("nombre") String nombre, @RequestParam("direccion") String direccion,
                               @RequestParam("email") String email, @RequestParam("username") String username,
                               @RequestParam("password") String password,@RequestParam("rol") String rol ) {


        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(password);
        Rol r = new Rol();
        r = rolService.findByNombreRol(rol);
        usuario.setRol_id(Integer.toString(r.getId()));
        usuarioService.crearUsuario(usuario);

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setEmail(email);
        cliente.setUsuario_id(Integer.toString(usuario.getId()));
        clienteService.crearCliente(cliente);

        String API_KEY = "eca1ad61d12248452578b2c4f1eda5963d6d94b5";
        Client client = new Client(API_KEY);
        try{
            client.sendMessage(

                    "20120994@topograpp.site",
                     ""+ email,
                    "Informacion de aceeso al sistema",
                    "Nombre de Usuario: " + username + "\n Contrasena: "+ password +"\n Enlace del sistema: topograpp.site ",
                    "<b>Hola Mundo desde SparkPost</b>");
        }
        catch (SparkPostException s){
            System.out.println("ERROR");
        }
        return "redirect:/clientes/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String borrarCliente(@PathVariable String id) {
        Cliente cliente = clienteService.buscarPorId(Long.parseLong(id));
        clienteService.borrarClientePorId(cliente);
        return "redirect:/clientes/";

    }

    @PostMapping("/modificar/")
    public String modificarCliente(@RequestParam("nombre2") String nombre, @RequestParam("direccion2") String direccion,
                                   @RequestParam("email2") String email, @RequestParam("id2") String id) {

        Cliente cliente = clienteService.buscarPorId(Long.parseLong(id));
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setEmail(email);
        clienteService.actualizarCliente(cliente);
        return "redirect:/clientes/";
    }

}
