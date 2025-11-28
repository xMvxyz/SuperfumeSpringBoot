package PerfulandiaV3.PerfulandiaV3.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PerfulandiaV3.PerfulandiaV3.Model.GerenteModel;
import PerfulandiaV3.PerfulandiaV3.Repository.GerenteRepository;


@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;

    public GerenteService(GerenteRepository gerenteRepository) {
        this.gerenteRepository = gerenteRepository;
    }
    
    public GerenteModel crearGerente(GerenteModel gerente) {
        return gerenteRepository.save(gerente);
    }

    public List<GerenteModel> obtenerTodos() {
        return gerenteRepository.findAll();
    }

    public GerenteModel actualizarGerente(int id, GerenteModel nuevo) {
        GerenteModel existente = gerenteRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setId(nuevo.getId());
            existente.setNombre(nuevo.getNombre());
            existente.setCorreo(nuevo.getCorreo());
            existente.setContraseña(nuevo.getContraseña());
            existente.setSucursal(nuevo.getSucursal());
            return gerenteRepository.save(existente);
        }
        return null;
    }

    public void eliminarUsuario(int id) {
        gerenteRepository.deleteById(id);
    }

    public GerenteModel buscarPorId(int id) {
        return gerenteRepository.findById(id).orElse(null);
    }
}
