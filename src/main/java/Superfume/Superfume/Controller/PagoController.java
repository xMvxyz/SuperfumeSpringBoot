package Superfume.Superfume.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import Superfume.Superfume.Dto.request.PagoRequestDto;
import Superfume.Superfume.Dto.response.PagoResponseDto;
import Superfume.Superfume.Mapper.PagoMapper;
import Superfume.Superfume.Model.PagoModel;
import Superfume.Superfume.Model.PedidoModel;
import Superfume.Superfume.Repository.PedidoRepository;
import Superfume.Superfume.Service.PagoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    private PagoService pagoService;
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public PagoResponseDto crear(@Valid @RequestBody PagoRequestDto pagoDto) {
        PedidoModel pedido = pedidoRepository.findById(pagoDto.getPedidoId())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Pedido no encontrado"));
        
        PagoModel pago = pagoService.crearPago(PagoMapper.toEntity(pagoDto, pedido));
        return PagoMapper.toResponseDto(pago);
    }

    @GetMapping
    public CollectionModel<EntityModel<PagoResponseDto>> listar() {
        var pagos = pagoService.obtenerTodos().stream()
            .map(PagoMapper::toResponseDto)
            .map(pago -> EntityModel.of(pago,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PagoController.class).buscarPorId(pago.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PagoController.class).listar()).withRel("pagos")
            )).collect(Collectors.toList());
        return CollectionModel.of(pagos,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PagoController.class).listar()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<PagoResponseDto> buscarPorId(@PathVariable int id) {
        PagoModel pago = pagoService.buscarPorId(id);
        if (pago == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Pago no encontrado");
        }
        PagoResponseDto dto = PagoMapper.toResponseDto(pago);
        return EntityModel.of(dto,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PagoController.class).buscarPorId(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PagoController.class).listar()).withRel("pagos")
        );
    }

    @GetMapping("/pedido/{pedidoId}")
    public List<PagoResponseDto> buscarPorPedido(@PathVariable int pedidoId) {
        return pagoService.buscarPorPedido(pedidoId).stream()
            .map(PagoMapper::toResponseDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/estado/{estado}")
    public List<PagoResponseDto> buscarPorEstado(@PathVariable PagoModel.EstadoPago estado) {
        return pagoService.buscarPorEstado(estado).stream()
            .map(PagoMapper::toResponseDto)
            .collect(Collectors.toList());
    }

    @PatchMapping("/{id}/estado")
    public PagoResponseDto actualizarEstado(
            @PathVariable int id, 
            @RequestParam PagoModel.EstadoPago estado,
            @RequestParam(required = false) String transaccionId) {
        PagoModel pago = pagoService.actualizarEstado(id, estado, transaccionId);
        if (pago == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Pago no encontrado");
        }
        return PagoMapper.toResponseDto(pago);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        pagoService.eliminarPago(id);
    }
}
