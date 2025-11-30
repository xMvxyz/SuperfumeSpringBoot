package Superfume.Superfume.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemResponseDto {
    private int id;
    private PerfumeResponseDto perfume;
    private int cantidad;
    private double precioUnitario;
    private double subtotal; // cantidad * precioUnitario
}
