package co.com.challenge.modules.usuario.mapper;

import co.com.challenge.modules.usuario.entity.Usuario;
import co.com.challenge.modules.usuario.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombreCompleto(usuario.getNombreCompleto());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setContrasena(usuario.getContrasena());
        usuarioDTO.setEsClienteFrecuente(usuario.getEsClienteFrecuente());
        return usuarioDTO;
    }

    public Usuario convertirAEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setEsClienteFrecuente(usuarioDTO.getEsClienteFrecuente());
        return usuario;
    }
}
