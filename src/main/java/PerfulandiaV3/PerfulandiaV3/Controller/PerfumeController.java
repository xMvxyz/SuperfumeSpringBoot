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
import PerfulandiaV3.PerfulandiaV3.Model.PerfumeModel;
import PerfulandiaV3.PerfulandiaV3.Service.PerfumeService;
import PerfulandiaV3.PerfulandiaV3.Dto.PerfumeDto;
import jakarta.validation.Valid;
import PerfulandiaV3.PerfulandiaV3.Mapper.PerfumeMapper;

@RestController
@RequestMapping("/Perfume")
public class PerfumeController {
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    public CollectionModel<EntityModel<PerfumeModel>> listar() {
        var perfumes = perfumeService.obtenerTodos().stream()
            .map(perfume -> EntityModel.of(perfume,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).buscarPorId(perfume.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).listar()).withRel("perfumes")
            )).collect(Collectors.toList());
        return CollectionModel.of(perfumes,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).listar()).withSelfRel()
        );
    }

    @PostMapping
    public PerfumeModel crear(@Valid @RequestBody PerfumeDto perfumeDto) {
        return perfumeService.crearPerfume(PerfumeMapper.toEntity(perfumeDto));
    }

    @GetMapping("/{id}")
    public EntityModel<PerfumeModel> buscarPorId(@PathVariable int id) {
        PerfumeModel perfume = perfumeService.buscarPorId(id);
        if (perfume == null) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Perfume no encontrado");
        }
        return EntityModel.of(perfume,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PerfumeController.class).listar()).withRel("perfumes")
        );
    }

    @PutMapping("/{id}")
    public PerfumeModel actualizar(@PathVariable int id, @Valid @RequestBody PerfumeDto perfumeDto) {
        return perfumeService.actualizarPerfume(id, PerfumeMapper.toEntity(perfumeDto));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        perfumeService.eliminarPerfume(id);
    }
}
