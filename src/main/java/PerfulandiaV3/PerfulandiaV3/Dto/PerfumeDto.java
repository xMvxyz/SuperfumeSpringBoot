package PerfulandiaV3.PerfulandiaV3.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PerfumeDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String marca;
    @Min(0)
    private double precio;
    @Min(0)
    private int cantidad;
}
