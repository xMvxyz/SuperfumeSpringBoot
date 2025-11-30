package Superfume.Superfume.Mapper;

import Superfume.Superfume.Dto.request.UsuarioRequestDto;
import Superfume.Superfume.Dto.response.RolResponseDto;
import Superfume.Superfume.Dto.response.UsuarioResponseDto;
import Superfume.Superfume.Model.RolModel;
import Superfume.Superfume.Model.UsuarioModel;

public class UsuarioMapper {
    public static UsuarioModel toEntity(UsuarioRequestDto dto, RolModel rol) {
        if (dto == null) return null;
        UsuarioModel u = new UsuarioModel();
        u.setNombre(dto.getNombre());
        u.setCorreo(dto.getCorreo());
        u.setRol(rol);
        u.setContrasena(dto.getContrasena());
        u.setTelefono(dto.getTelefono());
        u.setDireccion(dto.getDireccion());
        return u;
    }

    public static UsuarioResponseDto toResponseDto(UsuarioModel u) {
        if (u == null) return null;
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(u.getId());
        dto.setNombre(u.getNombre());
        dto.setCorreo(u.getCorreo());
        dto.setTelefono(u.getTelefono());
        dto.setDireccion(u.getDireccion());
        if (u.getRol() != null) {
            RolResponseDto rolDto = new RolResponseDto();
            rolDto.setId(u.getRol().getId());
            rolDto.setNombre(u.getRol().getNombre());
            rolDto.setDescripcion(u.getRol().getDescripcion());
            dto.setRol(rolDto);
        }
        return dto;
    }
}
