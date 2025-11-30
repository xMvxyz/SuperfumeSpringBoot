package Superfume.Superfume.Repository;
import org.springframework.stereotype.Repository;
import Superfume.Superfume.Model.EmpleadoModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Integer>{

}
