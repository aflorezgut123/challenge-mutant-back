package co.com.challenge.modules.usuario.controller.mapper;

import co.com.challenge.modules.usuario.mapper.UsuarioMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import co.com.challenge.modules.usuario.dto.UsuarioDTO;
import co.com.challenge.modules.usuario.entity.Usuario;

class UsuarioMapperTest {

    @Test
    void testConvertirADTO() {
        // Crea una instancia de Usuario
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombreCompleto("Juan Pérez");
        usuario.setNombreUsuario("juanperez");
        usuario.setContrasena("password123");
        usuario.setEsClienteFrecuente(true);

        // Convierte a UsuarioDTO
        UsuarioDTO usuarioDTO = new UsuarioMapper().convertirADTO(usuario);

        // Verifica los valores
        assertEquals(usuario.getId(), usuarioDTO.getId());
        assertEquals(usuario.getNombreCompleto(), usuarioDTO.getNombreCompleto());
        assertEquals(usuario.getNombreUsuario(), usuarioDTO.getNombreUsuario());
        assertEquals(usuario.getContrasena(), usuarioDTO.getContrasena());
        assertEquals(usuario.getEsClienteFrecuente(), usuarioDTO.getEsClienteFrecuente());
    }

    @Test
    void testConvertirAEntidad() {
        // Crea una instancia de UsuarioDTO
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1);
        usuarioDTO.setNombreCompleto("Juan Pérez");
        usuarioDTO.setNombreUsuario("juanperez");
        usuarioDTO.setContrasena("password123");
        usuarioDTO.setEsClienteFrecuente(true);

        // Convierte a Usuario
        Usuario usuario = new UsuarioMapper().convertirAEntidad(usuarioDTO);

        // Verifica los valores
        assertEquals(usuarioDTO.getId(), usuario.getId());
        assertEquals(usuarioDTO.getNombreCompleto(), usuario.getNombreCompleto());
        assertEquals(usuarioDTO.getNombreUsuario(), usuario.getNombreUsuario());
        assertEquals(usuarioDTO.getContrasena(), usuario.getContrasena());
        assertEquals(usuarioDTO.getEsClienteFrecuente(), usuario.getEsClienteFrecuente());
    }
}
