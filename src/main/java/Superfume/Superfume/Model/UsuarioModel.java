package Superfume.Superfume.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String nombre;
    @NotBlank @Email
    private String correo;
    @NotBlank
    private String contrasena;
    private String telefono;
    private String direccion;
    
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolModel rol;
}
