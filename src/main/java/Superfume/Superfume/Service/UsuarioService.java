package Superfume.Superfume.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Superfume.Superfume.Model.UsuarioModel;
import Superfume.Superfume.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
            
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel crearUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel actualizarUsuario(int id, UsuarioModel nuevo) {
        UsuarioModel existente = usuarioRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(nuevo.getNombre());
            existente.setCorreo(nuevo.getCorreo());
            existente.setRol(nuevo.getRol());
            existente.setContrasena(nuevo.getContrasena());
            existente.setTelefono(nuevo.getTelefono());
            existente.setDireccion(nuevo.getDireccion());
            return usuarioRepository.save(existente);
        }
        throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioModel buscarPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public UsuarioModel buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}
