package PerfulandiaV3.PerfulandiaV3.Controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PerfulandiaV3.PerfulandiaV3.Model.EmpleadoModel;
import PerfulandiaV3.PerfulandiaV3.Service.EmpleadoService;

@RestController
@RequestMapping("/Empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    @PostMapping
    public EmpleadoModel crear(@RequestBody EmpleadoModel empleado) {
        return empleadoService.crearEmpleado(empleado);
    }
    
    @GetMapping
    public CollectionModel<EntityModel<EmpleadoModel>> listar() {
        var empleados = empleadoService.obtenerTodos().stream()
            .map(empleado -> EntityModel.of(empleado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).buscarPorId(empleado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).listar()).withRel("empleados")
            )).collect(Collectors.toList());
        return CollectionModel.of(empleados,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).listar()).withSelfRel()
        );
    }
    
    @PutMapping("/{id}")
    public EmpleadoModel actualizarEmpleado(@PathVariable int id, @RequestBody EmpleadoModel nuevo) {
        return empleadoService.actualizarEmpleado(id, nuevo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        empleadoService.eliminarUsuario(id);
    }

    @GetMapping("/{id}")
    public EntityModel<EmpleadoModel> buscarPorId(@PathVariable int id) {
        EmpleadoModel empleado = empleadoService.buscarPorId(id);
        if (empleado == null) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Empleado no encontrado");
        }
        return EntityModel.of(empleado,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).listar()).withRel("empleados")
        );
    }
}
