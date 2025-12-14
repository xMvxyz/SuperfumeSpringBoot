package Superfume.Superfume.config;

import Superfume.Superfume.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Permitir peticiones OPTIONS (CORS preflight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"No se proporcionó token de autenticación\"}");
            return false;
        }

        try {
            String jwt = authorizationHeader.substring(7);
            String email = jwtUtil.extractUsername(jwt);
            
            if (email != null && jwtUtil.validateToken(jwt, email)) {
                // Token válido, agregar información al request
                request.setAttribute("email", email);
                request.setAttribute("rol", jwtUtil.extractRole(jwt));
                request.setAttribute("userId", jwtUtil.extractUserId(jwt));
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Token inválido o expirado\"}");
                return false;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Error al validar token: " + e.getMessage() + "\"}");
            return false;
        }
    }
}
