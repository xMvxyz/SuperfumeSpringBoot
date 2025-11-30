package Superfume.Superfume.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Superfume.Superfume.Model.PagoModel;
import Superfume.Superfume.Repository.PagoRepository;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    public PagoModel crearPago(PagoModel pago) {
        return pagoRepository.save(pago);
    }

    public List<PagoModel> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public PagoModel buscarPorId(int id) {
        return pagoRepository.findById(id).orElse(null);
    }

    public List<PagoModel> buscarPorPedido(int pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId);
    }

    public List<PagoModel> buscarPorEstado(PagoModel.EstadoPago estado) {
        return pagoRepository.findByEstado(estado);
    }

    public PagoModel actualizarEstado(int id, PagoModel.EstadoPago nuevoEstado, String transaccionId) {
        PagoModel pago = pagoRepository.findById(id).orElse(null);
        if (pago != null) {
            pago.setEstado(nuevoEstado);
            if (transaccionId != null) {
                pago.setTransaccionId(transaccionId);
            }
            return pagoRepository.save(pago);
        }
        return null;
    }

    public void eliminarPago(int id) {
        pagoRepository.deleteById(id);
    }
}
