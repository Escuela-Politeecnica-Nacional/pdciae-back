package epn.controllers;

import epn.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String token = authService.autenticar(email, password);

        if (token != null) {
            // Devolvemos el token en un JSON
            // return ResponseEntity.ok(Map.of("token", token));
            return ResponseEntity.ok("Ingreso éxitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Credenciales incorrectas");
        }
    }
}