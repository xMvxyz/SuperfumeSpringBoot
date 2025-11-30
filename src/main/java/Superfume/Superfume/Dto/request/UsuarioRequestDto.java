package Superfume.Superfume.Dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequestDto {
    @NotBlank
    private String nombre;
    
    @NotBlank @Email
    private String correo;
    
    @NotBlank
    private String contrasena;
    
    private String telefono;
    
    private String direccion;
    
    @NotNull
    private Integer rolId;
}
