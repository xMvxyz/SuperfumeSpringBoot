package Superfume.Superfume.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Superfume.Superfume.Model.CarritoItemModel;
import Superfume.Superfume.Model.CarritoModel;
import Superfume.Superfume.Model.CarritoModel.EstadoCarrito;
import Superfume.Superfume.Model.PerfumeModel;
import Superfume.Superfume.Model.UsuarioModel;
import Superfume.Superfume.Repository.CarritoItemRepository;
import Superfume.Superfume.Repository.CarritoRepository;
import Superfume.Superfume.Repository.PerfumeRepository;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CarritoItemRepository carritoItemRepository;
    
    @Autowired
    private PerfumeRepository perfumeRepository;

    // Obtener o crear carrito activo del usuario
    public CarritoModel obtenerCarritoActivo(UsuarioModel usuario) {
        Optional<CarritoModel> carritoOpt = carritoRepository.findByUsuarioIdAndEstado(
            usuario.getId(), EstadoCarrito.ACTIVO);
        
        if (carritoOpt.isPresent()) {
            return carritoOpt.get();
        }
        
        // Crear nuevo carrito si no existe
        CarritoModel nuevoCarrito = CarritoModel.builder()
            .usuario(usuario)
            .fechaCreacion(LocalDateTime.now())
            .estado(EstadoCarrito.ACTIVO)
            .build();
        
        return carritoRepository.save(nuevoCarrito);
    }

    // Agregar item al carrito
    @Transactional
    public CarritoModel agregarItem(int carritoId, int perfumeId, int cantidad) {
        CarritoModel carrito = carritoRepository.findById(carritoId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        
        PerfumeModel perfume = perfumeRepository.findById(perfumeId)
            .orElseThrow(() -> new RuntimeException("Perfume no encontrado"));
        
        // Verificar stock
        if (perfume.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        
        // Verificar si el item ya existe en el carrito
        Optional<CarritoItemModel> itemExistente = carritoItemRepository
            .findByCarritoIdAndPerfumeId(carritoId, perfumeId);
        
        if (itemExistente.isPresent()) {
            // Actualizar cantidad
            CarritoItemModel item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            carritoItemRepository.save(item);
        } else {
            // Crear nuevo item
            CarritoItemModel nuevoItem = CarritoItemModel.builder()
                .carrito(carrito)
                .perfume(perfume)
                .cantidad(cantidad)
                .precioUnitario(perfume.getPrecio())
                .build();
            carritoItemRepository.save(nuevoItem);
        }
        
        return carritoRepository.findById(carritoId).orElse(carrito);
    }

    // Actualizar cantidad de un item
    @Transactional
    public CarritoModel actualizarCantidadItem(int itemId, int nuevaCantidad) {
        CarritoItemModel item = carritoItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        
        if (nuevaCantidad <= 0) {
            carritoItemRepository.delete(item);
        } else {
            // Verificar stock
            if (item.getPerfume().getStock() < nuevaCantidad) {
                throw new RuntimeException("Stock insuficiente");
            }
            item.setCantidad(nuevaCantidad);
            carritoItemRepository.save(item);
        }
        
        return carritoRepository.findById(item.getCarrito().getId())
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    // Eliminar item del carrito
    @Transactional
    public void eliminarItem(int itemId) {
        carritoItemRepository.deleteById(itemId);
    }

    // Vaciar carrito
    @Transactional
    public void vaciarCarrito(int carritoId) {
        carritoItemRepository.deleteByCarritoId(carritoId);
    }

    // Cambiar estado del carrito
    public CarritoModel cambiarEstado(int carritoId, EstadoCarrito nuevoEstado) {
        CarritoModel carrito = carritoRepository.findById(carritoId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.setEstado(nuevoEstado);
        return carritoRepository.save(carrito);
    }

    // Obtener todos los carritos de un usuario
    public List<CarritoModel> obtenerCarritosPorUsuario(int usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    // Buscar por ID
    public CarritoModel buscarPorId(int id) {
        return carritoRepository.findById(id).orElse(null);
    }
}
