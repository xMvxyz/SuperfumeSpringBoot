package Superfume.Superfume.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoModel pedido;
    
    @Column(nullable = false)
    private double monto;
    
    @Column(nullable = false)
    private LocalDateTime fechaPago;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estado;
    
    private String transaccionId;
    
    public enum MetodoPago {
        EFECTIVO, TARJETA_CREDITO, TARJETA_DEBITO, TRANSFERENCIA, PAYPAL, MERCADOPAGO
    }
    
    public enum EstadoPago {
        PENDIENTE, PROCESANDO, COMPLETADO, FALLIDO, REEMBOLSADO
    }
}
