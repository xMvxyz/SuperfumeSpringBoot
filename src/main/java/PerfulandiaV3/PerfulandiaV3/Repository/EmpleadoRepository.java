package PerfulandiaV3.PerfulandiaV3.Repository;
import org.springframework.stereotype.Repository;
import PerfulandiaV3.PerfulandiaV3.Model.EmpleadoModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Integer>{

}
