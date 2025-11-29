package PerfulandiaV3.PerfulandiaV3.Mapper;

import PerfulandiaV3.PerfulandiaV3.Dto.EmpleadoDto;
import PerfulandiaV3.PerfulandiaV3.Model.EmpleadoModel;

public class EmpleadoMapper {
    public static EmpleadoModel toEntity(EmpleadoDto dto) {
        if (dto == null) return null;
        EmpleadoModel e = new EmpleadoModel();
        e.setNombre(dto.getNombre());
        e.setCorreo(dto.getCorreo());
        e.setContrasena(dto.getContrasena());
        e.setIdEmpleado(dto.getIdEmpleado());
        e.setSucursal(dto.getSucursal());
        e.setActivo(dto.isActivo());
        return e;
    }

    public static EmpleadoDto toDto(EmpleadoModel e) {
        if (e == null) return null;
        EmpleadoDto dto = new EmpleadoDto();
        dto.setNombre(e.getNombre());
        dto.setCorreo(e.getCorreo());
        dto.setContrasena(e.getContrasena());
        dto.setIdEmpleado(e.getIdEmpleado());
        dto.setSucursal(e.getSucursal());
        dto.setActivo(e.isActivo());
        return dto;
    }
}
