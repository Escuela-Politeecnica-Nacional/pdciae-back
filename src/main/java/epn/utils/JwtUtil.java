package epn.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // Clave secreta desde variable de entorno, o generada si está vacía
    private static final Key SECRET_KEY = initializeSecretKey();
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    private static Key initializeSecretKey() {
        String secretFromEnv = System.getenv("JWT_SECRET_KEY");
        
        if (secretFromEnv != null && !secretFromEnv.isBlank() && secretFromEnv.length() >= 32) {
            // Usar clave desde variable de entorno (mínimo 32 caracteres para HS256)
            return Keys.hmacShaKeyFor(secretFromEnv.getBytes(StandardCharsets.UTF_8));
        }
        
        // En desarrollo sin JWT_SECRET_KEY, generar una clave (pero se regenera cada startup)
        System.out.println("[JwtUtil] ADVERTENCIA: JWT_SECRET_KEY no configurada. Tokens se invalidarán al reiniciar.");
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extraerEmail(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            // Token expirado, inválido o con firma incorrecta
            throw new JwtException("Token JWT inválido o expirado: " + e.getMessage(), e);
        }
    }
}
