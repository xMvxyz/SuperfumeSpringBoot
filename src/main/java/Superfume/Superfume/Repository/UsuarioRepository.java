package Superfume.Superfume.Repository;
import org.springframework.stereotype.Repository;
import Superfume.Superfume.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioModel, Integer>{
    UsuarioModel findByCorreo(String correo);
}
