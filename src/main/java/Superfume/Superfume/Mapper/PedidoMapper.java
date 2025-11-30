package Superfume.Superfume.Mapper;

import Superfume.Superfume.Dto.request.PedidoRequestDto;
import Superfume.Superfume.Dto.response.PedidoResponseDto;
import Superfume.Superfume.Model.CarritoModel;
import Superfume.Superfume.Model.PedidoModel;
import Superfume.Superfume.Model.UsuarioModel;

public class PedidoMapper {
    // Crear pedido desde un carrito
    public static PedidoModel toEntity(CarritoModel carrito) {
        if (carrito == null) return null;
        PedidoModel pedido = new PedidoModel();
        pedido.setUsuario(carrito.getUsuario());
        pedido.setCarrito(carrito);
        pedido.setTotal(carrito.calcularTotal());
        pedido.setFechaPedido(java.time.LocalDateTime.now());
        pedido.setEstado(PedidoModel.EstadoPedido.PENDIENTE);
        return pedido;
    }

    public static PedidoResponseDto toResponseDto(PedidoModel pedido) {
        if (pedido == null) return null;
        PedidoResponseDto dto = new PedidoResponseDto();
        dto.setId(pedido.getId());
        dto.setUsuario(UsuarioMapper.toResponseDto(pedido.getUsuario()));
        
        if (pedido.getCarrito() != null) {
            dto.setCarrito(CarritoMapper.toResponseDto(pedido.getCarrito()));
        }
        
        dto.setTotal(pedido.getTotal());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());
        return dto;
    }
}
