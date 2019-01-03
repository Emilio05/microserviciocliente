package ce.pucmm.microserviciocliente.Controller;



import ce.pucmm.microserviciocliente.Model.Cliente;
import ce.pucmm.microserviciocliente.Model.Rol;
import ce.pucmm.microserviciocliente.Model.Usuario;
import ce.pucmm.microserviciocliente.Service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//        Rol rol = new Rol();
//                rol.setId(1);
//        rol.setNombreRol("Almacen");
//        rolService.crearRol(rol);
        return rolService.buscarTodosRoles();
    }

    @GetMapping(value = "/ver/{id}")
    public String rol(Model model, @PathVariable String id) {
        Rol r = rolService.buscarPorId(Long.parseLong(id));
        model.addAttribute("rol", r);
        return "rol";
    }


    @PostMapping("/")
    public String crearRol(@RequestParam("nombrerol") String rol) {
        Rol r = new Rol();
        r.setNombreRol(rol);
        rolService.crearRol(r);
        return "redirect:/roles/";
    }

    @PostMapping("/modificar/")
    public String modificarRol(@RequestParam("nombrerol2") String rol, @RequestParam("id2") String id) {
        Rol r = rolService.buscarPorId(Long.parseLong(id));
        r.setNombreRol(rol);
        rolService.actualizarRol(r);
        return "redirect:/roles/";
    }


    @PostMapping(value = "/eliminar/{id}")
    public String borrarRol(@PathVariable String id) {
        rolService.borrarRolPorId(Long.parseLong(id));
        return "redirect:/roles/";
    }
}
