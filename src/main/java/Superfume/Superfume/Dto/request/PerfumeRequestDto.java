package Superfume.Superfume.Dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PerfumeRequestDto {
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String marca;
    
    @NotNull
    @Positive
    private Double precio;
    
    @NotNull
    @Positive
    private Integer stock;
    
    private String descripcion;
    private String categoria;
}
