package epn.services;

import epn.Enums.Rol;
import epn.repositories.EstudianteRepository;
import epn.schemas.Estudiante;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerPorId(String id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
    }

    public Estudiante crear(Estudiante estudiante) {
        estudiante.setRol(Rol.ESTUDIANTE);
        return estudianteRepository.save(estudiante);
    }

    public Estudiante actualizar(String id, Estudiante estudianteActualizado) {
        Estudiante existente = obtenerPorId(id);
        existente.setNombre(estudianteActualizado.getNombre());
        existente.setApellido(estudianteActualizado.getApellido());
        existente.setContraseña(estudianteActualizado.getContraseña());
        existente.setRol(Rol.ESTUDIANTE);
        return estudianteRepository.save(existente);
    }

    public void eliminar(String id) {
        Estudiante existente = obtenerPorId(id);
        estudianteRepository.delete(existente);
    }
}
