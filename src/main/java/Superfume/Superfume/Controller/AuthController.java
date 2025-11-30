package Superfume.Superfume.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Superfume.Superfume.Dto.request.LoginRequestDto;
import Superfume.Superfume.Dto.request.RegisterRequestDto;
import Superfume.Superfume.Dto.response.LoginResponseDto;
import Superfume.Superfume.Dto.response.UsuarioResponseDto;
import Superfume.Superfume.Mapper.UsuarioMapper;
import Superfume.Superfume.Model.RolModel;
import Superfume.Superfume.Model.UsuarioModel;
import Superfume.Superfume.Repository.RolRepository;
import Superfume.Superfume.Service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginDto) {
        UsuarioModel usuario = usuarioService.buscarPorCorreo(loginDto.getCorreo());
        
        if (usuario == null) {
            return new LoginResponseDto(false, "Usuario no encontrado", null);
        }
        
        if (!usuario.getContrasena().equals(loginDto.getContrasena())) {
            return new LoginResponseDto(false, "Contraseña incorrecta", null);
        }
        
        UsuarioResponseDto usuarioDto = UsuarioMapper.toResponseDto(usuario);
        return new LoginResponseDto(true, "Login exitoso", usuarioDto);
    }

    @PostMapping("/register")
    public UsuarioResponseDto register(@Valid @RequestBody RegisterRequestDto registerDto) {
        // Verificar si el correo ya existe
        UsuarioModel usuarioExistente = usuarioService.buscarPorCorreo(registerDto.getCorreo());
        if (usuarioExistente != null) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "El correo ya está registrado");
        }
        
        // Buscar el rol
        RolModel rol = rolRepository.findById(registerDto.getRolId())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Rol no encontrado"));
        
        // Crear el usuario
        UsuarioModel nuevoUsuario = new UsuarioModel();
        nuevoUsuario.setNombre(registerDto.getNombre());
        nuevoUsuario.setCorreo(registerDto.getCorreo());
        nuevoUsuario.setContrasena(registerDto.getContrasena());
        nuevoUsuario.setTelefono(registerDto.getTelefono());
        nuevoUsuario.setDireccion(registerDto.getDireccion());
        nuevoUsuario.setRol(rol);
        
        UsuarioModel usuarioCreado = usuarioService.crearUsuario(nuevoUsuario);
        return UsuarioMapper.toResponseDto(usuarioCreado);
    }
}
