package Superfume.Superfume.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private CarritoModel carrito;
    
    @ManyToOne
    @JoinColumn(name = "perfume_id", nullable = false)
    private PerfumeModel perfume;
    
    private int cantidad;
    
    private double precioUnitario; // Precio al momento de agregarlo al carrito
}
