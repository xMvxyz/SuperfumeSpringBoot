package Superfume.Superfume.Controller;
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
import Superfume.Superfume.Model.RolModel;
import Superfume.Superfume.Model.UsuarioModel;
import Superfume.Superfume.Repository.RolRepository;
import Superfume.Superfume.Service.UsuarioService;
import Superfume.Superfume.Dto.UsuarioDto;
import jakarta.validation.Valid;
import Superfume.Superfume.Mapper.UsuarioMapper;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolRepository rolRepository;

    @PostMapping
    public UsuarioModel crear(@Valid @RequestBody UsuarioDto usuarioDto) {
        RolModel rol = rolRepository.findById(usuarioDto.getRolId())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Rol no encontrado"));
        return usuarioService.crearUsuario(UsuarioMapper.toEntity(usuarioDto, rol));
    }

    @GetMapping
    public CollectionModel<EntityModel<UsuarioModel>> listar() {
        var usuarios = usuarioService.obtenerTodos().stream()
            .map(usuario -> EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscarPorId(usuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withRel("usuarios")
            )).collect(Collectors.toList());
        return CollectionModel.of(usuarios,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<UsuarioModel> buscarPorId(@PathVariable int id) {
        UsuarioModel usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        return EntityModel.of(usuario,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withRel("usuarios")
        );
    }

    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioDto nuevoDto) {
        RolModel rol = rolRepository.findById(nuevoDto.getRolId())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Rol no encontrado"));
        return usuarioService.actualizarUsuario(id, UsuarioMapper.toEntity(nuevoDto, rol));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
    }
}
