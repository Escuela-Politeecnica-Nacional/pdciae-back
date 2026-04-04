package epn.services;

import epn.Enums.Rol;
import epn.repositories.AdminRepository;
import epn.schemas.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> listar() {
        return adminRepository.findAll();
    }

    public Admin obtenerPorId(String id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin no encontrado"));
    }

    public Admin crear(Admin admin) {
        admin.setRol(Rol.ADMIN);
        return adminRepository.save(admin);
    }

    public Admin actualizar(String id, Admin adminActualizado) {
        Admin existente = obtenerPorId(id);
        existente.setNombre(adminActualizado.getNombre());
        existente.setApellido(adminActualizado.getApellido());
        existente.setContraseña(adminActualizado.getContraseña());
        existente.setRol(Rol.ADMIN);
        return adminRepository.save(existente);
    }

    public void eliminar(String id) {
        Admin existente = obtenerPorId(id);
        adminRepository.delete(existente);
    }
}
