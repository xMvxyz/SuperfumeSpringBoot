package Superfume.Superfume.Mapper;

import java.util.stream.Collectors;
import Superfume.Superfume.Dto.response.CarritoItemResponseDto;
import Superfume.Superfume.Dto.response.CarritoResponseDto;
import Superfume.Superfume.Model.CarritoItemModel;
import Superfume.Superfume.Model.CarritoModel;

public class CarritoMapper {
    
    public static CarritoItemResponseDto toItemResponseDto(CarritoItemModel item) {
        if (item == null) return null;
        
        CarritoItemResponseDto dto = new CarritoItemResponseDto();
        dto.setId(item.getId());
        dto.setPerfume(PerfumeMapper.toResponseDto(item.getPerfume()));
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setSubtotal(item.getCantidad() * item.getPrecioUnitario());
        
        return dto;
    }
    
    public static CarritoResponseDto toResponseDto(CarritoModel carrito) {
        if (carrito == null) return null;
        
        CarritoResponseDto dto = new CarritoResponseDto();
        dto.setId(carrito.getId());
        dto.setUsuarioId(carrito.getUsuario().getId());
        dto.setUsuarioNombre(carrito.getUsuario().getNombre());
        dto.setFechaCreacion(carrito.getFechaCreacion());
        dto.setEstado(carrito.getEstado());
        
        if (carrito.getItems() != null) {
            dto.setItems(carrito.getItems().stream()
                .map(CarritoMapper::toItemResponseDto)
                .collect(Collectors.toList()));
            dto.setTotal(carrito.calcularTotal());
        } else {
            dto.setTotal(0.0);
        }
        
        return dto;
    }
}
