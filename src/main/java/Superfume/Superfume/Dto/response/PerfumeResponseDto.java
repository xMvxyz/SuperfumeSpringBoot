package Superfume.Superfume.Dto.response;

import lombok.Data;

@Data
public class PerfumeResponseDto {
    private Integer id;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
    private String descripcion;
    private String imagenUrl;
    private String genero;
    private String fragancia;
    private String notas;
    private String perfil;
}
