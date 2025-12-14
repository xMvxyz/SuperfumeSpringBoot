package Superfume.Superfume.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import Superfume.Superfume.Dto.request.CarritoItemRequestDto;
import Superfume.Superfume.Dto.response.CarritoResponseDto;
import Superfume.Superfume.Mapper.CarritoMapper;
import Superfume.Superfume.Model.CarritoModel;
import Superfume.Superfume.Model.UsuarioModel;
import Superfume.Superfume.Repository.UsuarioRepository;
import Superfume.Superfume.Service.CarritoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener carrito activo del usuario
    @GetMapping("/usuario/{usuarioId}")
    public EntityModel<CarritoResponseDto> obtenerCarritoActivo(@PathVariable int usuarioId) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        
        CarritoModel carrito = carritoService.obtenerCarritoActivo(usuario);
        CarritoResponseDto dto = CarritoMapper.toResponseDto(carrito);
        
        return EntityModel.of(dto);
    }

    // Agregar item al carrito
    @PostMapping("/{carritoId}/items")
    public CarritoResponseDto agregarItem(
            @PathVariable int carritoId,
            @Valid @RequestBody CarritoItemRequestDto itemDto) {
        
        CarritoModel carrito = carritoService.agregarItem(
            carritoId, 
            itemDto.getPerfumeId(), 
            itemDto.getCantidad()
        );
        
        return CarritoMapper.toResponseDto(carrito);
    }

    // Actualizar cantidad de un item
    @PutMapping("/items/{itemId}")
    public CarritoResponseDto actualizarCantidad(
            @PathVariable int itemId,
            @RequestParam int cantidad) {
        
        CarritoModel carrito = carritoService.actualizarCantidadItem(itemId, cantidad);
        return CarritoMapper.toResponseDto(carrito);
    }

    // Eliminar item del carrito
    @DeleteMapping("/items/{itemId}")
    public void eliminarItem(@PathVariable int itemId) {
        carritoService.eliminarItem(itemId);
    }

    // Vaciar carrito
    @DeleteMapping("/{carritoId}/vaciar")
    public void vaciarCarrito(@PathVariable int carritoId) {
        carritoService.vaciarCarrito(carritoId);
    }

    // Obtener carrito por ID
    @GetMapping("/{id}")
    public EntityModel<CarritoResponseDto> buscarPorId(@PathVariable int id) {
        CarritoModel carrito = carritoService.buscarPorId(id);
        if (carrito == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }
        CarritoResponseDto dto = CarritoMapper.toResponseDto(carrito);
        return EntityModel.of(dto);
    }
}
