package ce.pucmm.microserviciocliente.Controller;


import ce.pucmm.microserviciocliente.Model.Cliente;
import ce.pucmm.microserviciocliente.Model.Rol;
import ce.pucmm.microserviciocliente.Model.Usuario;
import ce.pucmm.microserviciocliente.Service.ClienteServiceImpl;
import ce.pucmm.microserviciocliente.Service.RolServiceImpl;
import ce.pucmm.microserviciocliente.Service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private RolServiceImpl rolService;


    @Autowired
    private ClienteServiceImpl clienteService;


    @RequestMapping("/todos")
    public List<Usuario> verTodosLosUsuarios() {


        return usuarioService.buscarTodosUsuarios();
    }



    @PostMapping("/crear/")
    public void crearUsuario(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("rol") String rol, HttpServletResponse httpResponse) throws IOException  {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        Rol r = new Rol();
        r = rolService.findByNombreRol(rol);
        u.setDeleted(false);
        u.setRol(r);
        usuarioService.crearUsuario(u);


        httpResponse.sendRedirect("http://localhost:8080/usuarios/");

    }

    @PostMapping("/modificar/")
    public void modificarUsuario(@RequestParam("username2") String username, @RequestParam("id2") String id,@RequestParam("password2") String password, @RequestParam("email2") String email, @RequestParam("rol2") String rol, HttpServletResponse httpResponse) throws IOException  {
        Usuario u = usuarioService.buscarPorId(Long.parseLong(id));
        Rol r = rolService.findByNombreRol(rol);
        u.setRol(r);
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);

        usuarioService.actualizarUsuario(u);

        httpResponse.sendRedirect("http://localhost:8080/usuarios/");

    }


    @PostMapping(value = "/eliminar/{id}")
    public void eliminarUsuario(@PathVariable String id, HttpServletResponse httpResponse) throws IOException   {
        usuarioService.borrarUsuarioPorId(Integer.parseInt(id));

        httpResponse.sendRedirect("http://localhost:8080/usuarios/");



    }

}
