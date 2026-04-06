package epn.pdciae_back.config;

import epn.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Extraer el header Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Quitar "Bearer "
            
            try {
                // 2. Validar el token y extraer email
                String email = jwtUtil.extraerEmail(token);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 3. Si es válido, "autenticamos" al usuario en el contexto de Spring
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            email, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (JwtException e) {
                // Token expirado, firmado incorrectamente o malformado
                // Registrar el error y continuar (Spring Security rechazará la request después)
                System.err.println("[JwtFilter] Error al validar JWT: " + e.getMessage());
                // No establecer autenticación, dejando que Spring Security dé 401
            }
        }

        filterChain.doFilter(request, response);
    }
}