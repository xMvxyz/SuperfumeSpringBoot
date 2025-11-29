package PerfulandiaV3.PerfulandiaV3.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PerfulandiaV3.PerfulandiaV3.Model.UsuarioModel;
import PerfulandiaV3.PerfulandiaV3.Repository.UsuarioRepository;

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
            existente.setId(nuevo.getId());
            existente.setNombre(nuevo.getNombre());
            existente.setCorreo(nuevo.getCorreo());
            existente.setRol(nuevo.getRol());
            existente.setContrasena(nuevo.getContrasena());
            return usuarioRepository.save(existente);
        }
        return null;
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioModel buscarPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
