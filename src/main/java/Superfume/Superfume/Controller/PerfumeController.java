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
import Superfume.Superfume.Dto.request.PerfumeRequestDto;
import Superfume.Superfume.Dto.response.PerfumeResponseDto;
import Superfume.Superfume.Model.PerfumeModel;
import Superfume.Superfume.Service.PerfumeService;
import jakarta.validation.Valid;
import Superfume.Superfume.Mapper.PerfumeMapper;

@RestController
@RequestMapping("/perfumes")
public class PerfumeController {
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    public CollectionModel<EntityModel<PerfumeResponseDto>> listar() {
        var perfumes = perfumeService.obtenerTodos().stream()
            .map(PerfumeMapper::toResponseDto)
            .map(perfume -> EntityModel.of(perfume,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).buscarPorId(perfume.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).listar()).withRel("perfumes")
            )).collect(Collectors.toList());
        return CollectionModel.of(perfumes,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).listar()).withSelfRel()
        );
    }

    @PostMapping
    public PerfumeResponseDto crear(@Valid @RequestBody PerfumeRequestDto perfumeDto) {
        PerfumeModel perfume = perfumeService.crearPerfume(PerfumeMapper.toEntity(perfumeDto));
        return PerfumeMapper.toResponseDto(perfume);
    }

    @GetMapping("/{id}")
    public EntityModel<PerfumeResponseDto> buscarPorId(@PathVariable int id) {
        PerfumeModel perfume = perfumeService.buscarPorId(id);
        if (perfume == null) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Perfume no encontrado");
        }
        PerfumeResponseDto dto = PerfumeMapper.toResponseDto(perfume);
        return EntityModel.of(dto,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).listar()).withRel("perfumes")
        );
    }

    @PutMapping("/{id}")
    public PerfumeResponseDto actualizar(@PathVariable int id, @Valid @RequestBody PerfumeRequestDto perfumeDto) {
        PerfumeModel perfume = perfumeService.actualizarPerfume(id, PerfumeMapper.toEntity(perfumeDto));
        return PerfumeMapper.toResponseDto(perfume);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        perfumeService.eliminarPerfume(id);
    }
}
