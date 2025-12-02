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
        UsuarioModel usuario = usuarioService.buscarPorCorreo(loginDto.getEmail());
        
        if (usuario == null) {
            return new LoginResponseDto(false, "Usuario no encontrado", null);
        }
        
        if (!usuario.getContrasena().equals(loginDto.getPassword())) {
            return new LoginResponseDto(false, "Contraseña incorrecta", null);
        }
        
        UsuarioResponseDto usuarioDto = UsuarioMapper.toResponseDto(usuario);
        return new LoginResponseDto(true, "Login exitoso", usuarioDto);
    }

    @PostMapping("/register")
    public LoginResponseDto register(@Valid @RequestBody RegisterRequestDto registerDto) {
        // Verificar si el correo ya existe
        UsuarioModel usuarioExistente = usuarioService.buscarPorCorreo(registerDto.getEmail());
        if (usuarioExistente != null) {
            return new LoginResponseDto(false, "El email ya está registrado", null);
        }
        
        // Obtener rol: si es admin.com, admin, sino cliente
        String roleStr = registerDto.getRole() != null ? registerDto.getRole() : "cliente";
        RolModel rol = rolRepository.findByNombre(roleStr)
            .orElse(rolRepository.findByNombre("cliente")
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST, "Rol no encontrado")));
        
        // Crear el usuario
        UsuarioModel nuevoUsuario = new UsuarioModel();
        nuevoUsuario.setNombre(registerDto.getName());
        nuevoUsuario.setCorreo(registerDto.getEmail());
        nuevoUsuario.setContrasena(registerDto.getPassword());
        nuevoUsuario.setTelefono(registerDto.getPhone());
        nuevoUsuario.setDireccion(registerDto.getAddress());
        nuevoUsuario.setRol(rol);
        
        UsuarioModel usuarioCreado = usuarioService.crearUsuario(nuevoUsuario);
        UsuarioResponseDto usuarioDto = UsuarioMapper.toResponseDto(usuarioCreado);
        return new LoginResponseDto(true, "Registro exitoso", usuarioDto);
    }
}
