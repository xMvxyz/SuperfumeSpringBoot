package Superfume.Superfume.Repository;

import Superfume.Superfume.Model.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Integer> {
    Optional<RolModel> findByNombre(String nombre);
}
