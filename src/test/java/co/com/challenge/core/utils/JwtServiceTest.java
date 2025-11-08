package co.com.challenge.core.utils;

import co.com.challenge.modules.usuario.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private Usuario user;

    private String secretKey = generarLLave(); // Asegúrate de usar una clave secreta válida

    private long jwtExpiration = 3600000; // 1 hora

    private long refreshExpiration = 7200000; // 2 horas

    public String generarLLave() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();

        // Usa reflexión para establecer el valor del campo privado 'secretKey'
        Field secretKeyField = JwtService.class.getDeclaredField("secretKey");
        secretKeyField.setAccessible(true);
        secretKeyField.set(jwtService, secretKey);

        // Usa reflexión para establecer el valor del campo privado 'jwtExpiration'
        Field jwtExpirationField = JwtService.class.getDeclaredField("jwtExpiration");
        jwtExpirationField.setAccessible(true);
        jwtExpirationField.set(jwtService, jwtExpiration);

        // Usa reflexión para establecer el valor del campo privado 'refreshExpiration'
        Field refreshExpirationField = JwtService.class.getDeclaredField("refreshExpiration");
        refreshExpirationField.setAccessible(true);
        refreshExpirationField.set(jwtService, refreshExpiration);
    }

    @Test
    void testGenerateToken() {
        when(user.getNombreUsuario()).thenReturn("testuser");
        String token = jwtService.generateToken(user);
        assertNotNull(token);
    }

    @Test
    void testExtractUsername() {
        when(user.getNombreUsuario()).thenReturn("testuser");
        String token = jwtService.generateToken(user);
        String username = jwtService.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testIsTokenValid() {
        when(user.getNombreUsuario()).thenReturn("testuser");
        String token = jwtService.generateToken(user);
        boolean isValid = jwtService.isTokenValid(token, user);
        assertTrue(isValid);
    }

    @Test
    void testIsTokenExpired() throws Exception {
        when(user.getNombreUsuario()).thenReturn("testuser");
        String token = jwtService.generateToken(user);

        // Usa reflexión para acceder al método privado 'isTokenExpired'
        Method isTokenExpiredMethod = JwtService.class.getDeclaredMethod("isTokenExpired", String.class);
        isTokenExpiredMethod.setAccessible(true);
        boolean isExpired = (boolean) isTokenExpiredMethod.invoke(jwtService, token);

        assertFalse(isExpired);
    }
}
