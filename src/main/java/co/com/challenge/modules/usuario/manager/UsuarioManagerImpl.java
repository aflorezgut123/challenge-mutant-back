package co.com.challenge.modules.usuario.manager;

import co.com.challenge.modules.usuario.data.UsuarioRepository;
import co.com.challenge.modules.usuario.entity.Usuario;
import co.com.challenge.modules.usuario.dto.UsuarioDTO;
import co.com.challenge.modules.usuario.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioManagerImpl implements UsuarioManager{

    private final UsuarioRepository usuarioRepositorio;

    private final UsuarioMapper usuarioMapper;

    private final PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll().stream()
                .map(usuarioMapper::convertirADTO)
                .toList();
    }

    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        final Usuario user = Usuario.builder()
                .nombreCompleto(usuario.getNombreCompleto())
                .nombreUsuario(usuario.getNombreUsuario())
                .contrasena(passwordEncoder.encode(usuario.getContrasena()))
                .esClienteFrecuente(false)
                .build();
        return usuarioRepositorio.save(user);
    }

    public Optional<Usuario> updateUsuario(Integer id, Usuario usuarioDetails) {
        return usuarioRepositorio.findById(id)
                .map(usuario -> {
                    usuario.setNombreCompleto(usuarioDetails.getNombreCompleto());
                    usuario.setNombreUsuario(usuarioDetails.getNombreUsuario());
                    usuario.setContrasena(usuarioDetails.getContrasena());
                    usuario.setEsClienteFrecuente(usuarioDetails.getEsClienteFrecuente());
                    return usuarioRepositorio.save(usuario);
                });
    }

    public boolean deleteUsuario(Integer id) {
        return usuarioRepositorio.findById(id)
                .map(usuario -> {
                    usuarioRepositorio.delete(usuario);
                    return true;
                }).orElse(false);
    }
}
