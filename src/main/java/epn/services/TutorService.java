package epn.services;

import epn.Enums.Rol;
import epn.repositories.TutorRepository;
import epn.schemas.Tutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<Tutor> listar() {
        return tutorRepository.findAll();
    }

    public Tutor obtenerPorId(String id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor no encontrado"));
    }

    public Tutor crear(Tutor tutor) {
        tutor.setRol(Rol.TUTOR);
        return tutorRepository.save(tutor);
    }

    public Tutor actualizar(String id, Tutor tutorActualizado) {
        Tutor existente = obtenerPorId(id);
        existente.setNombre(tutorActualizado.getNombre());
        existente.setApellido(tutorActualizado.getApellido());
        existente.setContraseña(tutorActualizado.getContraseña());
        existente.setRol(Rol.TUTOR);
        return tutorRepository.save(existente);
    }

    public void eliminar(String id) {
        Tutor existente = obtenerPorId(id);
        tutorRepository.delete(existente);
    }
}
