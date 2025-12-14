package Superfume.Superfume.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private boolean success;
    private String mensaje;
    private UsuarioResponseDto usuario;
    private String token;
    
    public LoginResponseDto(boolean success, String mensaje, UsuarioResponseDto usuario) {
        this.success = success;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }
}
