package co.com.challenge.modules.usuario.controller;

import co.com.challenge.modules.token.data.TokenRepository;
import co.com.challenge.modules.usuario.data.UsuarioRepository;
import co.com.challenge.modules.usuario.dto.UsuarioDTO;
import co.com.challenge.modules.usuario.manager.UsuarioManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioManagerImpl usuarioService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    void testGetAllUsuarios() throws Exception {
        UsuarioDTO usuario1 = new UsuarioDTO();
        UsuarioDTO usuario2 = new UsuarioDTO();
        when(usuarioService.obtenerTodosLosUsuarios()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());

        verify(usuarioService, times(1)).obtenerTodosLosUsuarios();
    }

    @Test
    void testDeleteUsuario() throws Exception {
        when(usuarioService.deleteUsuario(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/usuario/1"))
                .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).deleteUsuario(anyInt());
        verify(tokenRepository, times(1)).deleteByUsuarioId(anyInt());
        verify(usuarioRepository, times(1)).deleteById(anyInt());
    }
}
