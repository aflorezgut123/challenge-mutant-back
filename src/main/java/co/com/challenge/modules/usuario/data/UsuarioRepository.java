package co.com.challenge.modules.usuario.data;

import co.com.challenge.modules.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByNombreUsuario(String username);
}
