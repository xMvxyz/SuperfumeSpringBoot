package Superfume.Superfume.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Superfume.Superfume.Model.PedidoModel;
import Superfume.Superfume.Repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

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
