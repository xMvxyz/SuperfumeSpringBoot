package Superfume.Superfume.Dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoRequestDto {
    @NotNull(message = "El ID del carrito es obligatorio")
    private Integer carritoId;
}
