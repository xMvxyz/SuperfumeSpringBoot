package Superfume.Superfume.Mapper;

import Superfume.Superfume.Dto.request.PerfumeRequestDto;
import Superfume.Superfume.Dto.response.PerfumeResponseDto;
import Superfume.Superfume.Model.PerfumeModel;

public class PerfumeMapper {
    public static PerfumeModel toEntity(PerfumeRequestDto dto) {
        if (dto == null) return null;
        PerfumeModel p = new PerfumeModel();
        p.setNombre(dto.getNombre());
        p.setMarca(dto.getMarca());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setDescripcion(dto.getDescripcion());
        p.setImagenUrl(dto.getImagenUrl());
        p.setGenero(dto.getGenero());
        p.setFragancia(dto.getFragancia());
        p.setNotas(dto.getNotas());
        p.setPerfil(dto.getPerfil());
        return p;
    }

    public static PerfumeResponseDto toResponseDto(PerfumeModel p) {
        if (p == null) return null;
        PerfumeResponseDto dto = new PerfumeResponseDto();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setMarca(p.getMarca());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        dto.setDescripcion(p.getDescripcion());
        dto.setImagenUrl(p.getImagenUrl());
        dto.setGenero(p.getGenero());
        dto.setFragancia(p.getFragancia());
        dto.setNotas(p.getNotas());
        dto.setPerfil(p.getPerfil());
        return dto;
    }
}
