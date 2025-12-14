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
import Superfume.Superfume.util.JwtUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginDto) {
        UsuarioModel usuario = usuarioService.buscarPorCorreo(loginDto.getEmail());
        
        if (usuario == null) {
            return new LoginResponseDto(false, "Usuario no encontrado", null);
        }
        
        if (!usuario.getContrasena().equals(loginDto.getPassword())) {
            return new LoginResponseDto(false, "Contraseña incorrecta", null);
        }
        
        // Generar token JWT
        String token = jwtUtil.generateToken(
            usuario.getCorreo(), 
            usuario.getRol().getNombre(), 
            (long) usuario.getId()
        );
        
        UsuarioResponseDto usuarioDto = UsuarioMapper.toResponseDto(usuario);
        return new LoginResponseDto(true, "Login exitoso", usuarioDto, token);
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
        // Capitalizar primera letra para coincidir con la BD
        String roleCapitalized = roleStr.substring(0, 1).toUpperCase() + roleStr.substring(1).toLowerCase();
        RolModel rol = rolRepository.findByNombre(roleCapitalized)
            .orElse(rolRepository.findByNombre("Cliente")
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST, "Rol no encontrado")));
        
        // Crear el usuario
        UsuarioModel nuevoUsuario = new UsuarioModel();
        nuevoUsuario.setNombre(registerDto.getName());
        nuevoUsuario.setCorreo(registerDto.getEmail());
        nuevoUsuario.setContrasena(registerDto.getPassword());
        nuevoUsuario.setRut(registerDto.getRut());
        nuevoUsuario.setTelefono(registerDto.getPhone());
        nuevoUsuario.setDireccion(registerDto.getAddress());
        nuevoUsuario.setRol(rol);
        
        UsuarioModel usuarioCreado = usuarioService.crearUsuario(nuevoUsuario);
        
        // Generar token JWT para el nuevo usuario
        String token = jwtUtil.generateToken(
            usuarioCreado.getCorreo(), 
            usuarioCreado.getRol().getNombre(), 
            (long) usuarioCreado.getId()
        );
        
        UsuarioResponseDto usuarioDto = UsuarioMapper.toResponseDto(usuarioCreado);
        return new LoginResponseDto(true, "Registro exitoso", usuarioDto, token);
    }
}
