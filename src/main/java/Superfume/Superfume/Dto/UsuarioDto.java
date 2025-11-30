package Superfume.Superfume.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDto {
    @NotBlank
    private String nombre;
    @NotBlank @Email
    private String correo;
    @NotNull
    private Integer rolId;
    @NotBlank
    private String contrasena;
}
