package Superfume.Superfume.Dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PedidoRequestDto {
    @NotNull
    private Integer usuarioId;
    
    @NotNull
    private Integer perfumeId;
    
    @NotNull
    @Positive
    private Integer cantidad;
}
