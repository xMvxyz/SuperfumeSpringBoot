package Superfume.Superfume.Mapper;

import Superfume.Superfume.Dto.request.PedidoRequestDto;
import Superfume.Superfume.Dto.response.PedidoResponseDto;
import Superfume.Superfume.Model.PedidoModel;
import Superfume.Superfume.Model.PerfumeModel;
import Superfume.Superfume.Model.UsuarioModel;

public class PedidoMapper {
    public static PedidoModel toEntity(PedidoRequestDto dto, UsuarioModel usuario, PerfumeModel perfume) {
        if (dto == null) return null;
        PedidoModel pedido = new PedidoModel();
        pedido.setUsuario(usuario);
        pedido.setPerfume(perfume);
        pedido.setCantidad(dto.getCantidad());
        pedido.setPrecioUnitario(perfume.getPrecio());
        pedido.setTotal(perfume.getPrecio() * dto.getCantidad());
        pedido.setFechaPedido(java.time.LocalDateTime.now());
        pedido.setEstado(PedidoModel.EstadoPedido.PENDIENTE);
        return pedido;
    }

    public static PedidoResponseDto toResponseDto(PedidoModel pedido) {
        if (pedido == null) return null;
        PedidoResponseDto dto = new PedidoResponseDto();
        dto.setId(pedido.getId());
        dto.setUsuario(UsuarioMapper.toResponseDto(pedido.getUsuario()));
        dto.setPerfume(PerfumeMapper.toResponseDto(pedido.getPerfume()));
        dto.setCantidad(pedido.getCantidad());
        dto.setPrecioUnitario(pedido.getPrecioUnitario());
        dto.setTotal(pedido.getTotal());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());
        return dto;
    }
}
