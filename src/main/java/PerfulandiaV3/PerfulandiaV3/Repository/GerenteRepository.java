package PerfulandiaV3.PerfulandiaV3.Repository;
import org.springframework.stereotype.Repository;
import PerfulandiaV3.PerfulandiaV3.Model.GerenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GerenteRepository extends JpaRepository <GerenteModel, Integer> {

}
