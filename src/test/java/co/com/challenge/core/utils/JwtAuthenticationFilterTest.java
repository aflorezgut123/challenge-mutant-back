package co.com.challenge.core.utils;

import co.com.challenge.modules.token.data.TokenRepository;
import co.com.challenge.modules.token.entity.Token;
import co.com.challenge.modules.usuario.data.UsuarioRepository;
import co.com.challenge.modules.usuario.entity.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.io.IOException;
import java.util.Optional;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private UsuarioRepository userRepository;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        // Configura el comportamiento del request mock
        when(request.getServletPath()).thenReturn("/api/v1/test");
        when(request.getHeader("Authorization")).thenReturn("Bearer test-token");

        // Configura el comportamiento del jwtService mock
        when(jwtService.extractUsername("test-token")).thenReturn("testuser");

        // Configura el comportamiento del userDetailsService mock
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);

        // Configura el comportamiento del tokenRepository mock
        when(tokenRepository.findByToken("test-token")).thenReturn(Optional.of(new Token( 41, "dsafasdfafd23423fsdfsf", Token.TokenType.BEARER, false, false, new Usuario())));

        // Configura el comportamiento del userRepository mock
        Usuario usuario = new Usuario();
        when(userRepository.findByNombreUsuario("testuser")).thenReturn(Optional.of(usuario));

        // Configura el comportamiento del jwtService mock para validar el token
        when(jwtService.isTokenValid("test-token", usuario)).thenReturn(true);

        // Ejecuta el método doFilterInternal
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verifica que el filtro haya continuado con la cadena de filtros
        verify(filterChain).doFilter(request, response);
    }
}
