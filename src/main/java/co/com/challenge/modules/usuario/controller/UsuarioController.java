package co.com.challenge.modules.usuario.controller;

import co.com.challenge.modules.token.data.TokenRepository;
import co.com.challenge.modules.usuario.data.UsuarioRepository;
import co.com.challenge.modules.usuario.dto.UsuarioDTO;
import co.com.challenge.modules.usuario.entity.Usuario;
import co.com.challenge.modules.usuario.manager.UsuarioManagerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

        private final UsuarioManagerImpl usuarioService;


        private final TokenRepository tokenRepository;


        private final UsuarioRepository usuarioRepository;

        @GetMapping
        public List<UsuarioDTO> getAllUsuarios() {
            return usuarioService.obtenerTodosLosUsuarios();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
            Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
            return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public Usuario createUsuario(@RequestBody Usuario usuario) {
            return usuarioService.createUsuario(usuario);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
            return usuarioService.updateUsuario(id, usuarioDetails)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
            if (usuarioService.deleteUsuario(id)) {
                tokenRepository.deleteByUsuarioId(id);
                usuarioRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}
