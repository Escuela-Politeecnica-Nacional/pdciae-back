package epn.controllers;

import epn.schemas.Admin;
import epn.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> listar() {
        return adminService.listar();
    }

    @GetMapping("/{id}")
    public Admin obtenerPorId(@PathVariable String id) {
        return adminService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Admin> crear(@RequestBody Admin admin) {
        Admin creado = adminService.crear(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public Admin actualizar(@PathVariable String id, @RequestBody Admin admin) {
        return adminService.actualizar(id, admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        adminService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
