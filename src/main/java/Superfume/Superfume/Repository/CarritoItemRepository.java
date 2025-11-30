package Superfume.Superfume.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Superfume.Superfume.Model.CarritoItemModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItemModel, Integer> {
    List<CarritoItemModel> findByCarritoId(int carritoId);
    Optional<CarritoItemModel> findByCarritoIdAndPerfumeId(int carritoId, int perfumeId);
    void deleteByCarritoId(int carritoId);
}
