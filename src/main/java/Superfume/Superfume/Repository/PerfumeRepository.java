package Superfume.Superfume.Repository;
import org.springframework.stereotype.Repository;
import Superfume.Superfume.Model.PerfumeModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PerfumeRepository extends JpaRepository <PerfumeModel, Integer> {

}
