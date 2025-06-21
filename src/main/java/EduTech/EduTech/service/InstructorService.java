package EduTech.EduTech.service;

import EduTech.EduTech.dto.InstructorDTO;
import EduTech.EduTech.model.Curso;
import EduTech.EduTech.model.Instructor;
import EduTech.EduTech.repository.CursoRepository;
import EduTech.EduTech.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Realizado por: Felipe Escobar

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public String almacenar(Instructor instructor) {
        try {
            if (instructor.getRut() == null || instructor.getRut().isBlank()) {
                return "El RUT es obligatorio.";
            }

            if (instructor.getCorreo() == null || instructor.getCorreo().isBlank()) {
                return "El correo es obligatorio.";
            }

            if (instructor.getContrasena() == null || instructor.getContrasena().isBlank()) {
                return "La contraseña es obligatoria.";
            }

            if (instructor.getNombre() == null || instructor.getNombre().isBlank() ||
                instructor.getApellido() == null || instructor.getApellido().isBlank()) {
                return "El nombre y apellido son obligatorios.";
            }

            if (instructorRepository.existsById(instructor.getRut())) {
                return "Ya existe un instructor registrado con ese RUT.";
            }

            instructorRepository.save(instructor);
            return "Instructor registrado correctamente.";

        } catch (Exception e) {
            return "Error interno: " + e.getMessage();
        }
    }

    public List<InstructorDTO> listarDTO() {
        return instructorRepository.findAll()
                                .stream()
                                .map(InstructorDTO::new)
                                .toList();
    }

    public String eliminar(String rutInstructor, Integer idCurso) {
        Instructor instructor = instructorRepository.findById(rutInstructor).orElse(null);
        Curso curso = cursoRepository.findById(idCurso).orElse(null);

        if (instructor == null) {
            return "Instructor no encontrado.";
        }

        if (curso == null) {
            return "Curso no encontrado.";
        }

        if (!instructor.getCursos().contains(curso)) {
            return "El instructor no tiene asignado ese curso.";
        }

        instructor.getCursos().remove(curso);
        instructorRepository.save(instructor);

        return "Curso eliminado del instructor correctamente.";
    }

}
