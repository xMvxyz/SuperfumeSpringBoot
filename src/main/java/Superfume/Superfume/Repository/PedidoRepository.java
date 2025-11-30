package Superfume.Superfume.Repository;

import Superfume.Superfume.Model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
    List<PedidoModel> findByUsuarioId(int usuarioId);
    List<PedidoModel> findByEstado(PedidoModel.EstadoPedido estado);
}
