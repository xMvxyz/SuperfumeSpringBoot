package Superfume.Superfume.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;
    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoItemModel> items;
    
    private LocalDateTime fechaCreacion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCarrito estado;
    
    public enum EstadoCarrito {
        ACTIVO,      // Carrito en uso
        PROCESANDO,  // En proceso de checkout
        CONVERTIDO   // Ya convertido a pedido
    }
    
    // Método para calcular el total del carrito
    public double calcularTotal() {
        return items != null ? items.stream()
            .mapToDouble(item -> item.getPrecioUnitario() * item.getCantidad())
            .sum() : 0.0;
    }
}
