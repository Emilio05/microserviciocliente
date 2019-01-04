package ce.pucmm.microserviciocliente.Controller;



import ce.pucmm.microserviciocliente.Model.Cliente;
import ce.pucmm.microserviciocliente.Model.Rol;
import ce.pucmm.microserviciocliente.Model.Usuario;
import ce.pucmm.microserviciocliente.Service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolServiceImpl rolService;

    @GetMapping(value = "/")
    public String roles(Model model) {
        List<Rol> roles = new ArrayList<>();
        roles = rolService.buscarTodosRoles();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @RequestMapping("/todos")
    public List<Rol> verTodosLosRoles() {

        return rolService.buscarTodosRoles();
    }


    @PostMapping("/crear/")
    public void crearRol(@RequestParam("nombrerol") String rol, HttpServletResponse httpResponse) throws IOException  {
        Rol r = new Rol();
        r.setNombreRol(rol);
        rolService.crearRol(r);

        httpResponse.sendRedirect("http://localhost:8080/roles/");

    }

    @PostMapping("/modificar/")
    public void modificarRol(@RequestParam("nombrerol2") String rol, @RequestParam("id2") String id, HttpServletResponse httpResponse) throws IOException  {
        Rol r = rolService.buscarPorId(Long.parseLong(id));
        r.setNombreRol(rol);
        rolService.actualizarRol(r);

        httpResponse.sendRedirect("http://localhost:8080/roles/");

    }


    @PostMapping(value = "/eliminar/{id}")
    public void borrarRol(@PathVariable String id,  HttpServletResponse httpResponse) throws IOException  {
        rolService.borrarRolPorId(Long.parseLong(id));

        httpResponse.sendRedirect("http://localhost:8080/roles/");

    }
}
