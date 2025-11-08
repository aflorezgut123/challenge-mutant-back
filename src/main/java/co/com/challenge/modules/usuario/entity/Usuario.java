package co.com.challenge.modules.usuario.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombreUsuario"})})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(nullable = false)
    private String nombreCompleto;
    private String nombreUsuario;
    private String contrasena;
    private Boolean esClienteFrecuente;
}
