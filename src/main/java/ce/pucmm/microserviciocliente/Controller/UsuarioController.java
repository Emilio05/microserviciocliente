package ce.pucmm.microserviciocliente.Controller;


import ce.pucmm.microserviciocliente.Model.Rol;
import ce.pucmm.microserviciocliente.Model.Usuario;
import ce.pucmm.microserviciocliente.Service.RolServiceImpl;
import ce.pucmm.microserviciocliente.Service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private RolServiceImpl rolService;

    @GetMapping(value = "/")
    public String usuarios(Model model) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioService.buscarTodosUsuarios();
        List<Rol> roles = rolService.buscarTodosRoles();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);

        return "usuarios";
    }

    @RequestMapping("/todos")
    public List<Usuario> verTodosLosUsuarios() {

//        Usuario usuario = new Usuario();
//                usuario.setId(1);
//        usuario.setUsername("emilio05");
//        usuario.setPassword("emilin05");
//        usuario.setEmail("emilio.rojas05@gmail.com");
//        usuario.setRol_id("1");
//        usuarioService.crearUsuario(usuario);
        return usuarioService.usuariosAlmacen();
    }


    @PostMapping("/")
    public String crearUsuario(@RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam("email") String email, @RequestParam("rol") String rol) {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        Rol r = new Rol();
        r = rolService.findByNombreRol(rol);
        u.setRol_id(Integer.toString(r.getId()));
        usuarioService.crearUsuario(u);
        return "redirect:/usuarios/";
    }
}
