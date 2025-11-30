package Superfume.Superfume.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Superfume.Superfume.Model.PerfumeModel;
import Superfume.Superfume.Repository.PerfumeRepository;

@Service
public class PerfumeService {
    @Autowired
    private PerfumeRepository perfumeRepository;

    public List<PerfumeModel> obtenerTodos() {
        return perfumeRepository.findAll();
    }

    public PerfumeModel crearPerfume(PerfumeModel perfume) {
        return perfumeRepository.save(perfume);
    }

    public PerfumeModel buscarPorId(int id) {
        return perfumeRepository.findById(id).orElse(null);
    }

    public PerfumeModel actualizarPerfume(int id, PerfumeModel nuevo) {
        PerfumeModel existente = perfumeRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setId(nuevo.getId());
            existente.setNombre(nuevo.getNombre());
            existente.setMarca(nuevo.getMarca());
            existente.setPrecio(nuevo.getPrecio());
            existente.setCantidad(nuevo.getCantidad());
            return perfumeRepository.save(existente);
        }
        return null;
    }

    public void eliminarPerfume(int id) {
        perfumeRepository.deleteById(id);
    }
}
