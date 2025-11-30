package Superfume.Superfume.Dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import Superfume.Superfume.Model.CarritoModel.EstadoCarrito;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponseDto {
    private int id;
    private int usuarioId;
    private String usuarioNombre;
    private List<CarritoItemResponseDto> items;
    private LocalDateTime fechaCreacion;
    private EstadoCarrito estado;
    private double total;
}
