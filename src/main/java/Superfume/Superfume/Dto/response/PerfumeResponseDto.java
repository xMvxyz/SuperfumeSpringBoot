package Superfume.Superfume.Dto.response;

import lombok.Data;

@Data
public class PerfumeResponseDto {
    private int id;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
    private String descripcion;
    private String categoria;
}
