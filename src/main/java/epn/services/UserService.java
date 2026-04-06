package epn.services;

import epn.Enums.Rol;
import epn.repositories.AdminRepository;
import epn.schemas.Admin;
import epn.schemas.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Admin> listarAdmins() {
        return adminRepository.findAll();
    }

    public Admin obtenerAdminPorId(String id) {
        return findByIdOrThrow(adminRepository, id, "Admin no encontrado");
    }

    public Admin crearAdmin(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email " + admin.getEmail() + " ya está en uso.");
        }

        // Hashear password
        String passwordHasheada = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(passwordHasheada);

        admin.setRol(Rol.ADMIN);
        return adminRepository.save(admin);
    }

    public Admin actualizarAdmin(String id, Admin adminActualizado) {
        Admin existente = obtenerAdminPorId(id);
        copyEditableFields(existente, adminActualizado);
        existente.setRol(Rol.ADMIN);
        return adminRepository.save(existente);
    }

    @SuppressWarnings("null")
    public void eliminarAdmin(String id) {
        Admin existente = obtenerAdminPorId(id);
        // Borrado lógico: cambiar estado a INACTIVO en lugar de eliminar físicamente
        existente.setEstado(epn.Enums.Estados.INACTIVO);
        adminRepository.save(existente);
    }

    @SuppressWarnings("null")
    private <T extends Usuario> T findByIdOrThrow(MongoRepository<T, String> repository, String id,
            String notFoundMessage) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundMessage));
    }

    private void copyEditableFields(Usuario existing, Usuario updated) {
        existing.setEmail(updated.getEmail());
        existing.setNombre(updated.getNombre());
        existing.setApellido(updated.getApellido());
        
        // Encriptar password si se proporciona
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            String passwordHasheada = passwordEncoder.encode(updated.getPassword());
            existing.setPassword(passwordHasheada);
        }
    }
}