package Superfume.Superfume.Dto.response;

import Superfume.Superfume.Model.PagoModel;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PagoResponseDto {
    private int id;
    private int pedidoId;
    private double monto;
    private LocalDateTime fechaPago;
    private PagoModel.MetodoPago metodoPago;
    private PagoModel.EstadoPago estado;
    private String transaccionId;
}
