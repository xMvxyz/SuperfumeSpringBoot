package PerfulandiaV3.PerfulandiaV3.Mapper;

import PerfulandiaV3.PerfulandiaV3.Dto.PerfumeDto;
import PerfulandiaV3.PerfulandiaV3.Model.PerfumeModel;

public class PerfumeMapper {
    public static PerfumeModel toEntity(PerfumeDto dto) {
        if (dto == null) return null;
        return new PerfumeModel(0, dto.getNombre(), dto.getMarca(), dto.getPrecio(), dto.getCantidad());
    }

    public static PerfumeDto toDto(PerfumeModel p) {
        if (p == null) return null;
        PerfumeDto dto = new PerfumeDto();
        dto.setNombre(p.getNombre());
        dto.setMarca(p.getMarca());
        dto.setPrecio(p.getPrecio());
        dto.setCantidad(p.getCantidad());
        return dto;
    }
}
