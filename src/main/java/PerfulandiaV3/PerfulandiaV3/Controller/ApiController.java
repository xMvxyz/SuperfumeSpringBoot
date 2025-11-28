package PerfulandiaV3.PerfulandiaV3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import PerfulandiaV3.PerfulandiaV3.Model.EmpleadoModel;
import PerfulandiaV3.PerfulandiaV3.Model.UsuarioModel;
import PerfulandiaV3.PerfulandiaV3.Model.PerfumeModel;
import PerfulandiaV3.PerfulandiaV3.Model.GerenteModel;
import PerfulandiaV3.PerfulandiaV3.Service.EmpleadoService;
import PerfulandiaV3.PerfulandiaV3.Service.UsuarioService;
import PerfulandiaV3.PerfulandiaV3.Service.PerfumeService;
import PerfulandiaV3.PerfulandiaV3.Service.GerenteService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PerfumeService perfumeService;
    @Autowired
    private GerenteService gerenteService;

    // --- Empleado ---
    @GetMapping("/empleados")
    public List<EmpleadoModel> getEmpleados() {
        return empleadoService.obtenerTodos();
    }
    @GetMapping("/empleados/{id}")
    public EmpleadoModel getEmpleado(@PathVariable int id) {
        return empleadoService.buscarPorId(id);
    }
    @PostMapping("/empleados")
    public EmpleadoModel createEmpleado(@RequestBody EmpleadoModel empleado) {
        return empleadoService.crearEmpleado(empleado);
    }
    @PutMapping("/empleados/{id}")
    public EmpleadoModel updateEmpleado(@PathVariable int id, @RequestBody EmpleadoModel empleado) {
        return empleadoService.actualizarEmpleado(id, empleado);
    }
    @DeleteMapping("/empleados/{id}")
    public void deleteEmpleado(@PathVariable int id) {
        empleadoService.eliminarUsuario(id);
    }

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

    // --- Gerente ---
    @GetMapping("/gerentes")
    public List<GerenteModel> getGerentes() {
        return gerenteService.obtenerTodos();
    }
    @GetMapping("/gerentes/{id}")
    public GerenteModel getGerente(@PathVariable int id) {
        return gerenteService.buscarPorId(id);
    }
    @PostMapping("/gerentes")
    public GerenteModel createGerente(@RequestBody GerenteModel gerente) {
        return gerenteService.crearGerente(gerente);
    }
    @PutMapping("/gerentes/{id}")
    public GerenteModel updateGerente(@PathVariable int id, @RequestBody GerenteModel gerente) {
        return gerenteService.actualizarGerente(id, gerente);
    }
    @DeleteMapping("/gerentes/{id}")
    public void deleteGerente(@PathVariable int id) {
        gerenteService.eliminarUsuario(id);
    }
}
