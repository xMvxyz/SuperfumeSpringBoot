package Superfume.Superfume.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpleadoDto {
    @NotBlank
    private String nombre;
    @NotBlank @Email
    private String correo;
    @NotBlank
    private String contrasena;
    private int idEmpleado;
    @NotBlank
    private String sucursal;
    private boolean activo = true;
}
