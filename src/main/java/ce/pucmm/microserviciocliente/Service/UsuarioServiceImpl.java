package ce.pucmm.microserviciocliente.Service;


import ce.pucmm.microserviciocliente.Model.Usuario;
import ce.pucmm.microserviciocliente.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;




    @Transactional
    public void crearUsuario(Usuario usuario) {
        usuario.setPassword((usuario.getPassword()));
        usuarioRepository.save(usuario);
    }




//    @Override
//    public String usuarioLogueado() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails)userDetails).getUsername();
//        }
//
//        return null;
//    }

//    public void autoLogin(String username, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            System.out.println("Login funciona para: " + username);
//        }
//    }

    public boolean login(String username, String password){

        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios){

            if(username == usuario.getUsername() && password == usuario.getPassword()){
                return true;
            }
        }
        return false;
    }

    public void actualizarUsuario(Usuario usuario) {
        crearUsuario(usuario);
    }

    public void borrarUsuarioPorId(long id) {
        usuarioRepository.deleteById(id);
    }

    public void borrarTodosLosUsuarios() {
        usuarioRepository.deleteAll();
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean usuarioExiste(Usuario usuario) {
        return findByUsername(usuario.getUsername()) != null;
    }

    public List<Usuario> usuariosAlmacen(){
        return usuarioRepository.usuariosAlmacen();
    }

}
