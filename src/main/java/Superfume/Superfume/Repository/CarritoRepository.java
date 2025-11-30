package Superfume.Superfume.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Superfume.Superfume.Model.CarritoModel;
import Superfume.Superfume.Model.CarritoModel.EstadoCarrito;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoModel, Integer> {
    Optional<CarritoModel> findByUsuarioIdAndEstado(int usuarioId, EstadoCarrito estado);
    List<CarritoModel> findByUsuarioId(int usuarioId);
    List<CarritoModel> findByEstado(EstadoCarrito estado);
}
