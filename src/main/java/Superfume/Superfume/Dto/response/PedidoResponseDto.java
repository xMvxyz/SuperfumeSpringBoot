package Superfume.Superfume.Dto.response;

import Superfume.Superfume.Model.PedidoModel;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PedidoResponseDto {
    private int id;
    private UsuarioResponseDto usuario;
    private CarritoResponseDto carrito;
    private double total;
    private LocalDateTime fechaPedido;
    private PedidoModel.EstadoPedido estado;
}
