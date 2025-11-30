package Superfume.Superfume.Mapper;

import Superfume.Superfume.Dto.request.PagoRequestDto;
import Superfume.Superfume.Dto.response.PagoResponseDto;
import Superfume.Superfume.Model.PagoModel;
import Superfume.Superfume.Model.PedidoModel;

public class PagoMapper {
    public static PagoModel toEntity(PagoRequestDto dto, PedidoModel pedido) {
        if (dto == null) return null;
        PagoModel pago = new PagoModel();
        pago.setPedido(pedido);
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(java.time.LocalDateTime.now());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstado(PagoModel.EstadoPago.PROCESANDO);
        return pago;
    }

    public static PagoResponseDto toResponseDto(PagoModel pago) {
        if (pago == null) return null;
        PagoResponseDto dto = new PagoResponseDto();
        dto.setId(pago.getId());
        dto.setPedidoId(pago.getPedido().getId());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstado(pago.getEstado());
        dto.setTransaccionId(pago.getTransaccionId());
        return dto;
    }
}
