package Superfume.Superfume.Dto.request;

import Superfume.Superfume.Model.PagoModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PagoRequestDto {
    @NotNull
    private Integer pedidoId;
    
    @NotNull
    @Positive
    private Double monto;
    
    @NotNull
    private PagoModel.MetodoPago metodoPago;
}
