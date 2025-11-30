package Superfume.Superfume;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Superfume.Superfume.Repository.PerfumeRepository;
import Superfume.Superfume.Service.PerfumeService;
import Superfume.Superfume.Model.PerfumeModel;
import Superfume.Superfume.Repository.UsuarioRepository;
import Superfume.Superfume.Service.UsuarioService;
import Superfume.Superfume.Model.UsuarioModel;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

// @SpringBootTest
class SuperfumeApplicationTests {

    @Mock
    private PerfumeRepository perfumeRepository;

    @InjectMocks
    private PerfumeService perfumeService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    

    public  SuperfumeApplicationTests() {
        MockitoAnnotations.openMocks(this);
    }

/*-------------------------------PERFUMES------------------------------------------*/
    @Test
    void testObtenerTodosPerfumes() {
        List<PerfumeModel> perfumes = new ArrayList<>();
        perfumes.add(new PerfumeModel());
        when(perfumeRepository.findAll()).thenReturn(perfumes);

        List<PerfumeModel> resultado = perfumeService.obtenerTodos();

        assertEquals(perfumes, resultado);
        verify(perfumeRepository, times(1)).findAll();
        System.out.println("testObtenerTodosPerfumes pasó correctamente");
    }

    @Test
    void testObtenerPerfumePorId() {
        PerfumeModel perfume = new PerfumeModel();
        perfume.setId(1);
        perfume.setNombre("Aqua");
        when(perfumeRepository.findById(1)).thenReturn(Optional.of(perfume));

        PerfumeModel resultado = perfumeService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Aqua", resultado.getNombre());
        verify(perfumeRepository, times(1)).findById(1);
        System.out.println("testObtenerPerfumePorId pasó correctamente");
    }

    @Test
    void testObtenerPerfumePorIdNoExiste() {
        when(perfumeRepository.findById(99)).thenReturn(Optional.empty());

        PerfumeModel resultado = perfumeService.buscarPorId(99);

        assertNull(resultado);
        verify(perfumeRepository, times(1)).findById(99);
        System.out.println("testObtenerPerfumePorIdNoExiste pasó correctamente");
    }

    @Test
    void testCrearPerfume() {
        PerfumeModel perfume = new PerfumeModel();
        perfume.setId(2);
        perfume.setNombre("Blue");
        when(perfumeRepository.save(perfume)).thenReturn(perfume);

        PerfumeModel resultado = perfumeService.crearPerfume(perfume);

        assertNotNull(resultado);
        assertEquals("Blue", resultado.getNombre());
        verify(perfumeRepository, times(1)).save(perfume);
        System.out.println("testCrearPerfume pasó correctamente");
    }

    @Test
    void testActualizarPerfume() {
        int id = 3;
        PerfumeModel existente = new PerfumeModel();
        existente.setId(id);
        existente.setNombre("Old");
        PerfumeModel nuevo = new PerfumeModel();
        nuevo.setId(id);
        nuevo.setNombre("New");
        when(perfumeRepository.findById(id)).thenReturn(Optional.of(existente));
        when(perfumeRepository.save(any(PerfumeModel.class))).thenReturn(nuevo);

        PerfumeModel resultado = perfumeService.actualizarPerfume(id, nuevo);

        assertNotNull(resultado);
        assertEquals("New", resultado.getNombre());
        verify(perfumeRepository, times(1)).findById(id);
        verify(perfumeRepository, times(1)).save(any(PerfumeModel.class));
        System.out.println("testActualizarPerfume pasó correctamente");
    }

    @Test
    void testActualizarPerfumeNoExiste() {
        int id = 99;
        PerfumeModel nuevo = new PerfumeModel();
        nuevo.setId(id);
        nuevo.setNombre("No Existe");
        when(perfumeRepository.findById(id)).thenReturn(Optional.empty());

        PerfumeModel resultado = perfumeService.actualizarPerfume(id, nuevo);

        assertNull(resultado);
        verify(perfumeRepository, times(1)).findById(id);
        verify(perfumeRepository, never()).save(any(PerfumeModel.class));
        System.out.println("testActualizarPerfumeNoExiste pasó correctamente");
    }

    @Test
    void testEliminarPerfume() {
        int id = 4;
        doNothing().when(perfumeRepository).deleteById(id);

        perfumeService.eliminarPerfume(id);

        verify(perfumeRepository, times(1)).deleteById(id);
        System.out.println("testEliminarPerfume pasó correctamente");
    }

/*-----------------------Usuarios----------------------------------------------*/

    @Test
    void testObtenerTodosUsuarios() {
        List<UsuarioModel> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioModel());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioModel> resultado = usuarioService.obtenerTodos();

        assertEquals(usuarios, resultado);
        verify(usuarioRepository, times(1)).findAll();
        System.out.println("testObtenerTodosUsuarios pasó correctamente");
    }

    @Test
    void testObtenerUsuarioPorId() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1);
        usuario.setNombre("Carlos");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        UsuarioModel resultado = usuarioService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
        verify(usuarioRepository, times(1)).findById(1);
        System.out.println("testObtenerUsuarioPorId pasó correctamente");
    }

    @Test
    void testObtenerUsuarioPorIdNoExiste() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        UsuarioModel resultado = usuarioService.buscarPorId(99);

        assertNull(resultado);
        verify(usuarioRepository, times(1)).findById(99);
        System.out.println("testObtenerUsuarioPorIdNoExiste pasó correctamente");
    }

    @Test
    void testCrearUsuario() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(2);
        usuario.setNombre("Lucia");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioModel resultado = usuarioService.crearUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("Lucia", resultado.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
        System.out.println("testCrearUsuario pasó correctamente");
    }

    @Test
    void testActualizarUsuario() {
        int id = 3;
        UsuarioModel existente = new UsuarioModel();
        existente.setId(id);
        existente.setNombre("Mario");
        UsuarioModel nuevo = new UsuarioModel();
        nuevo.setId(id);
        nuevo.setNombre("Mario Actualizado");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(nuevo);

        UsuarioModel resultado = usuarioService.actualizarUsuario(id, nuevo);

        assertNotNull(resultado);
        assertEquals("Mario Actualizado", resultado.getNombre());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(any(UsuarioModel.class));
        System.out.println("testActualizarUsuario pasó correctamente");
    }

    @Test
    void testActualizarUsuarioNoExiste() {
        int id = 99;
        UsuarioModel nuevo = new UsuarioModel();
        nuevo.setId(id);
        nuevo.setNombre("No Existe");
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        UsuarioModel resultado = usuarioService.actualizarUsuario(id, nuevo);

        assertNull(resultado);
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, never()).save(any(UsuarioModel.class));
        System.out.println("testActualizarUsuarioNoExiste pasó correctamente");
    }

    @Test
    void testEliminarUsuario() {
        int id = 4;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.eliminarUsuario(id);

        verify(usuarioRepository, times(1)).deleteById(id);
        System.out.println("testEliminarUsuario pasó correctamente");
    }
}
