package epn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import epn.repositories.AdminRepository;
import epn.utils.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String autenticar(String email, String passwordEnviado) {
        return adminRepository.findByEmail(email)
                .filter(admin -> passwordEncoder.matches(passwordEnviado, admin.getPassword()))
                .map(admin -> jwtUtil.generarToken(admin.getEmail()))
                .orElse(null);
    }
}