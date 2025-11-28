package PerfulandiaV3.PerfulandiaV3.Repository;
import org.springframework.stereotype.Repository;
import PerfulandiaV3.PerfulandiaV3.Model.PerfumeModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PerfumeRepository extends JpaRepository <PerfumeModel, Integer> {

}
