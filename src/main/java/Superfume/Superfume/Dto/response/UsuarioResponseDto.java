package Superfume.Superfume.Dto.response;

import lombok.Data;

@Data
public class UsuarioResponseDto {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private RolResponseDto rol;
}
