package Superfume.Superfume.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Superfume.Superfume.Model.EmpleadoModel;
import Superfume.Superfume.Repository.EmpleadoRepository;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public EmpleadoModel crearEmpleado(EmpleadoModel empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<EmpleadoModel> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public EmpleadoModel actualizarEmpleado(int id, EmpleadoModel nuevo) {
        EmpleadoModel existente = empleadoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(nuevo.getNombre());
                existente.setCorreo(nuevo.getCorreo());
                existente.setContrasena(nuevo.getContrasena());
            existente.setIdEmpleado(nuevo.getIdEmpleado());
            existente.setSucursal(nuevo.getSucursal());
            return empleadoRepository.save(existente);
        }
        return null;
    }

    public void eliminarUsuario(int id) {
        empleadoRepository.deleteById(id);
    }

    public EmpleadoModel buscarPorId(int id) {
        return empleadoRepository.findById(id).orElse(null);
    }
}
