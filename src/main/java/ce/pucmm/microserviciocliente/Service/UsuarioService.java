package ce.pucmm.microserviciocliente.Service;


import ce.pucmm.microserviciocliente.Model.Usuario;

import java.util.List;

public interface UsuarioService {

    void crearUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);

    void borrarUsuarioPorId(long id);

    void borrarTodosLosUsuarios();

    List<Usuario> buscarTodosUsuarios();

    Usuario buscarPorId(long id);

    Usuario findByUsername(String username);

    boolean usuarioExiste(Usuario usuario);

    List<Usuario> usuariosAlmacen();


}
