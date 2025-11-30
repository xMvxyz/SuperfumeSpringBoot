package Superfume.Superfume.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;
    
    @OneToOne
    @JoinColumn(name = "carrito_id")
    private CarritoModel carrito; // Carrito que originó este pedido
    
    private double total;
    
    @Column(nullable = false)
    private LocalDateTime fechaPedido;
    
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    
    public enum EstadoPedido {
        PENDIENTE, CONFIRMADO, ENVIADO, ENTREGADO, CANCELADO
    }
}
