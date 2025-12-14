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
import Superfume.Superfume.Dto.request.UsuarioRequestDto;
import Superfume.Superfume.Dto.response.UsuarioResponseDto;
import Superfume.Superfume.Model.RolModel;
import Superfume.Superfume.Model.UsuarioModel;
import Superfume.Superfume.Repository.RolRepository;
import Superfume.Superfume.Service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import Superfume.Superfume.Mapper.UsuarioMapper;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolRepository rolRepository;

    @PostMapping
    public UsuarioResponseDto crear(@Valid @RequestBody UsuarioRequestDto usuarioDto) {
        RolModel rol = rolRepository.findById(usuarioDto.getRolId())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Rol no encontrado"));
        UsuarioModel usuario = usuarioService.crearUsuario(UsuarioMapper.toEntity(usuarioDto, rol));
        return UsuarioMapper.toResponseDto(usuario);
    }

    @GetMapping
    public CollectionModel<EntityModel<UsuarioResponseDto>> listar() {
        var usuarios = usuarioService.obtenerTodos().stream()
            .map(UsuarioMapper::toResponseDto)
            .map(usuario -> EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscarPorId(usuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withRel("usuarios")
            )).collect(Collectors.toList());
        return CollectionModel.of(usuarios,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<UsuarioResponseDto> buscarPorId(@PathVariable int id) {
        UsuarioModel usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        UsuarioResponseDto dto = UsuarioMapper.toResponseDto(usuario);
        return EntityModel.of(dto,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withRel("usuarios")
        );
    }

    @PutMapping("/{id}")
    public UsuarioResponseDto actualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioRequestDto nuevoDto) {
        RolModel rol = rolRepository.findById(nuevoDto.getRolId())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Rol no encontrado"));
        UsuarioModel usuario = usuarioService.actualizarUsuario(id, UsuarioMapper.toEntity(nuevoDto, rol));
        return UsuarioMapper.toResponseDto(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
    }
}
