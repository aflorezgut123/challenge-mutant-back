package co.com.challenge.modules.login.controller;

import co.com.challenge.modules.login.dto.AuthRequest;
import co.com.challenge.modules.login.dto.TokenResponse;
import co.com.challenge.modules.login.manager.LoginManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private LoginManagerImpl loginManager;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrar() {
        // Configura el comportamiento del servicio mock
        AuthRequest request = new AuthRequest("pruebaTest", "prueba", "fasdf2344");
        TokenResponse tokenResponse = new TokenResponse("test-token","refresh-test", "pruebaTest");
        when(loginManager.registrar(request)).thenReturn(tokenResponse);

        // Llama al método del controlador
        ResponseEntity<TokenResponse> response = authController.registrar(request);

        // Verifica el resultado
        assertEquals(ResponseEntity.ok(tokenResponse), response);
    }

    @Test
     void testAuthenticate() {
        // Configura el comportamiento del servicio mock
        AuthRequest request = new AuthRequest("pruebaTest", "prueba", "fasdf2344");
        TokenResponse tokenResponse = new TokenResponse("test-token","refresh-test", "pruebaTest");
        when(loginManager.authenticate(request)).thenReturn(tokenResponse);

        // Llama al método del controlador
        ResponseEntity<TokenResponse> response = authController.authenticate(request);

        // Verifica el resultado
        assertEquals(ResponseEntity.ok(tokenResponse), response);
    }
}