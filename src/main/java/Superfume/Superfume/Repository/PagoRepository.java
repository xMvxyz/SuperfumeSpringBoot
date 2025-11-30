package Superfume.Superfume.Repository;

import Superfume.Superfume.Model.PagoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Integer> {
    List<PagoModel> findByPedidoId(int pedidoId);
    List<PagoModel> findByEstado(PagoModel.EstadoPago estado);
    List<PagoModel> findByMetodoPago(PagoModel.MetodoPago metodoPago);
}
