package epn.controllers;

import epn.schemas.Documento;
import epn.services.DocumentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping
    public List<Documento> listar() {
        return documentoService.listar();
    }

    @GetMapping("/{id}")
    public Documento obtenerPorId(@PathVariable String id) {
        return documentoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Documento> crear(@RequestBody Documento documento) {
        Documento creado = documentoService.crear(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public Documento actualizar(@PathVariable String id, @RequestBody Documento documento) {
        return documentoService.actualizar(id, documento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        documentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
