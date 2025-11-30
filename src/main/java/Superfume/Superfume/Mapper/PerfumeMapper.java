package Superfume.Superfume.Mapper;

import Superfume.Superfume.Dto.PerfumeDto;
import Superfume.Superfume.Model.PerfumeModel;

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
