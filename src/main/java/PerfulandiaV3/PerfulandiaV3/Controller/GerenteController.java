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
import PerfulandiaV3.PerfulandiaV3.Model.GerenteModel;
import PerfulandiaV3.PerfulandiaV3.Service.GerenteService;

@RestController
@RequestMapping("/Gerente")
public class GerenteController {
    @Autowired
    private GerenteService gerenteService;
    
    
    @PostMapping
    public GerenteModel crear(@RequestBody GerenteModel gerente) {
        return gerenteService.crearGerente(gerente);
    }
    
    @GetMapping
    public CollectionModel<EntityModel<GerenteModel>> listar() {
        var gerentes = gerenteService.obtenerTodos().stream()
            .map(gerente -> EntityModel.of(gerente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GerenteController.class).buscarPorId(gerente.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GerenteController.class).listar()).withRel("gerentes")
            )).collect(Collectors.toList());
        return CollectionModel.of(gerentes,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GerenteController.class).listar()).withSelfRel()
        );
    }
    
    @PutMapping("/{id}")
    public GerenteModel actualizarGerente(@PathVariable int id, @RequestBody GerenteModel nuevo) {
        return gerenteService.actualizarGerente(id, nuevo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        gerenteService.eliminarUsuario(id);
    }

    @GetMapping("/{id}")
    public EntityModel<GerenteModel> buscarPorId(@PathVariable int id) {
        GerenteModel gerente = gerenteService.buscarPorId(id);
        if (gerente == null) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Gerente no encontrado");
        }
        return EntityModel.of(gerente,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GerenteController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GerenteController.class).listar()).withRel("gerentes")
        );
    }
}
