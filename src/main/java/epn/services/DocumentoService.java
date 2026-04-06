package epn.services;

import epn.repositories.DocumentoRepository;
import epn.schemas.Documento;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> listar() {
        return documentoRepository.findAll();
    }

    @SuppressWarnings("null")
    public Documento obtenerPorId(String id) {
        return documentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento no encontrado"));
    }

    @SuppressWarnings("null")
    public Documento crear(Documento documento) {
        return documentoRepository.save(documento);
    }

    public Documento actualizar(String id, Documento documentoActualizado) {
        Documento existente = obtenerPorId(id);
        existente.setNombreDocumento(documentoActualizado.getNombreDocumento());
        existente.setMateria(documentoActualizado.getMateria());
        existente.setFacultad(documentoActualizado.getFacultad());
        return documentoRepository.save(existente);
    }

    @SuppressWarnings("null")
    public void eliminar(String id) {
        Documento existente = obtenerPorId(id);
        documentoRepository.delete(existente);
    }
}
