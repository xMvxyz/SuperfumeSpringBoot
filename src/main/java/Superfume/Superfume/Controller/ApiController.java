package Superfume.Superfume.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import Superfume.Superfume.Model.UsuarioModel;
import Superfume.Superfume.Model.PerfumeModel;
import Superfume.Superfume.Service.UsuarioService;
import Superfume.Superfume.Service.PerfumeService;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PerfumeService perfumeService;
    

    // --- Usuario ---
    @GetMapping("/usuarios")
    public List<UsuarioModel> getUsuarios() {
        return usuarioService.obtenerTodos();
    }
    @GetMapping("/usuarios/{id}")
    public UsuarioModel getUsuario(@PathVariable int id) {
        return usuarioService.buscarPorId(id);
    }
    @PostMapping("/usuarios")
    public UsuarioModel createUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.crearUsuario(usuario);
    }
    @PutMapping("/usuarios/{id}")
    public UsuarioModel updateUsuario(@PathVariable int id, @RequestBody UsuarioModel usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
    }

    // --- Perfume ---
    @GetMapping("/perfumes")
    public List<PerfumeModel> getPerfumes() {
        return perfumeService.obtenerTodos();
    }
    @GetMapping("/perfumes/{id}")
    public PerfumeModel getPerfume(@PathVariable int id) {
        return perfumeService.buscarPorId(id);
    }
    @PostMapping("/perfumes")
    public PerfumeModel createPerfume(@RequestBody PerfumeModel perfume) {
        return perfumeService.crearPerfume(perfume);
    }
    @PutMapping("/perfumes/{id}")
    public PerfumeModel updatePerfume(@PathVariable int id, @RequestBody PerfumeModel perfume) {
        return perfumeService.actualizarPerfume(id, perfume);
    }
    @DeleteMapping("/perfumes/{id}")
    public void deletePerfume(@PathVariable int id) {
        perfumeService.eliminarPerfume(id);
    }

    
}
