package epn.controllers;

import epn.schemas.Tutor;
import epn.services.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public List<Tutor> listar() {
        return tutorService.listar();
    }

    @GetMapping("/{id}")
    public Tutor obtenerPorId(@PathVariable String id) {
        return tutorService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Tutor> crear(@RequestBody Tutor tutor) {
        Tutor creado = tutorService.crear(tutor);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public Tutor actualizar(@PathVariable String id, @RequestBody Tutor tutor) {
        return tutorService.actualizar(id, tutor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        tutorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
