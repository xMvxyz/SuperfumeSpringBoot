package Superfume.Superfume.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import Superfume.Superfume.Dto.request.PedidoRequestDto;
import Superfume.Superfume.Dto.response.PedidoResponseDto;
import Superfume.Superfume.Mapper.PedidoMapper;
import Superfume.Superfume.Model.CarritoModel;
import Superfume.Superfume.Model.PedidoModel;
import Superfume.Superfume.Repository.CarritoRepository;
import Superfume.Superfume.Service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private CarritoRepository carritoRepository;

    // Crear pedido desde un carrito
    @PostMapping
    public PedidoResponseDto crear(@Valid @RequestBody PedidoRequestDto pedidoDto) {
        CarritoModel carrito = carritoRepository.findById(pedidoDto.getCarritoId())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Carrito no encontrado"));
        
        if (carrito.getEstado() != CarritoModel.EstadoCarrito.ACTIVO) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "El carrito no está activo");
        }
        
        if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "El carrito está vacío");
        }
        
        PedidoModel pedido = pedidoService.crearPedidoDesdeCarrito(carrito);
        return PedidoMapper.toResponseDto(pedido);
    }

    @GetMapping
    public CollectionModel<EntityModel<PedidoResponseDto>> listar() {
        var pedidos = pedidoService.obtenerTodos().stream()
            .map(PedidoMapper::toResponseDto)
            .map(pedido -> EntityModel.of(pedido,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).buscarPorId(pedido.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).listar()).withRel("pedidos")
            )).collect(Collectors.toList());
        return CollectionModel.of(pedidos,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).listar()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<PedidoResponseDto> buscarPorId(@PathVariable int id) {
        PedidoModel pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Pedido no encontrado");
        }
        PedidoResponseDto dto = PedidoMapper.toResponseDto(pedido);
        return EntityModel.of(dto,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoController.class).listar()).withRel("pedidos")
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<PedidoResponseDto> buscarPorUsuario(@PathVariable int usuarioId) {
        return pedidoService.buscarPorUsuario(usuarioId).stream()
            .map(PedidoMapper::toResponseDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/estado/{estado}")
    public List<PedidoResponseDto> buscarPorEstado(@PathVariable PedidoModel.EstadoPedido estado) {
        return pedidoService.buscarPorEstado(estado).stream()
            .map(PedidoMapper::toResponseDto)
            .collect(Collectors.toList());
    }

    @PatchMapping("/{id}/estado")
    public PedidoResponseDto actualizarEstado(@PathVariable int id, @RequestParam PedidoModel.EstadoPedido estado) {
        PedidoModel pedido = pedidoService.actualizarEstado(id, estado);
        if (pedido == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Pedido no encontrado");
        }
        return PedidoMapper.toResponseDto(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        pedidoService.eliminarPedido(id);
    }
}
