package Superfume.Superfume.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Superfume.Superfume.Model.CarritoItemModel;
import Superfume.Superfume.Model.CarritoModel;
import Superfume.Superfume.Model.PedidoModel;
import Superfume.Superfume.Repository.CarritoRepository;
import Superfume.Superfume.Repository.PedidoRepository;
import Superfume.Superfume.Repository.PerfumeRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private PerfumeRepository perfumeRepository;

    @Transactional
    public PedidoModel crearPedidoDesdeCarrito(CarritoModel carrito) {
        // Verificar y descontar stock de todos los items
        for (CarritoItemModel item : carrito.getItems()) {
            if (item.getPerfume().getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + item.getPerfume().getNombre());
            }
            
            // Descontar stock
            item.getPerfume().setStock(item.getPerfume().getStock() - item.getCantidad());
            perfumeRepository.save(item.getPerfume());
        }
        
        // Crear pedido
        PedidoModel pedido = PedidoModel.builder()
            .usuario(carrito.getUsuario())
            .carrito(carrito)
            .total(carrito.calcularTotal())
            .fechaPedido(LocalDateTime.now())
            .estado(PedidoModel.EstadoPedido.PENDIENTE)
            .build();
        
        // Cambiar estado del carrito a CONVERTIDO
        carrito.setEstado(CarritoModel.EstadoCarrito.CONVERTIDO);
        carritoRepository.save(carrito);
        
        return pedidoRepository.save(pedido);
    }

    public PedidoModel crearPedido(PedidoModel pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel buscarPorId(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<PedidoModel> buscarPorUsuario(int usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public List<PedidoModel> buscarPorEstado(PedidoModel.EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public PedidoModel actualizarEstado(int id, PedidoModel.EstadoPedido nuevoEstado) {
        PedidoModel pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public void eliminarPedido(int id) {
        pedidoRepository.deleteById(id);
    }
}
