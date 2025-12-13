package Superfume.Superfume.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfumeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String marca;
    
    @Column(name = "imagen_url")
    private String imagenUrl = "/img/producto_01.jpg";

    private String genero;

    @NotBlank
    private String fragancia;
    
    @NotBlank
    private String notas;
    
    @NotBlank
    private String perfil;
    
    @Min(0)
    private double precio;
    
    @Min(0)
    private int stock = 0;
    
    private String descripcion;
}
