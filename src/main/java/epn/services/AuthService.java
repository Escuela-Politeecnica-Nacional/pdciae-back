package epn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epn.repositories.AdminRepository;
import epn.utils.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public String autenticar(String email, String password) {
        return adminRepository.findAll().stream()
                .filter(a -> a.getEmail().equals(email) && a.getPassword().equals(password))
                .findFirst()
                .map(a -> jwtUtil.generarToken(a.getEmail()))
                .orElse(null);
    }
}