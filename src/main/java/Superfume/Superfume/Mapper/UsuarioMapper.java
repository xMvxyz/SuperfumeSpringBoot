package Superfume.Superfume.Mapper;

import Superfume.Superfume.Dto.UsuarioDto;
import Superfume.Superfume.Model.UsuarioModel;

public class UsuarioMapper {
    public static UsuarioModel toEntity(UsuarioDto dto) {
        if (dto == null) return null;
        UsuarioModel u = new UsuarioModel();
        u.setNombre(dto.getNombre());
        u.setCorreo(dto.getCorreo());
        u.setRol(dto.getRol());
        u.setContrasena(dto.getContrasena());
        return u;
    }

    public static UsuarioDto toDto(UsuarioModel u) {
        if (u == null) return null;
        UsuarioDto dto = new UsuarioDto();
        dto.setNombre(u.getNombre());
        dto.setCorreo(u.getCorreo());
        dto.setRol(u.getRol());
        dto.setContrasena(u.getContrasena());
        return dto;
    }
}
