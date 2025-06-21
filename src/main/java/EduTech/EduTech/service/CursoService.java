package EduTech.EduTech.service;

import EduTech.EduTech.dto.CursoDTO;
import EduTech.EduTech.model.Curso;
import EduTech.EduTech.model.Instructor;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.CursoRepository;
import EduTech.EduTech.repository.InstructorRepository;
import EduTech.EduTech.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Realizado por: Felipe Escobar

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public String almacenar(Curso curso) {
        if (curso.getNombre() == null || curso.getNombre().isBlank()) {
            return "El nombre del curso es obligatorio.";
        }

        if (curso.getPrecio() == null || curso.getPrecio() <= 0) {
            return "El precio del curso es obligatorio y debe ser mayor a 0.";
        }

        Curso existente = cursoRepository.findByNombre(curso.getNombre());

        if (existente != null) {
            return "El curso " + curso.getNombre() + " ya existe.";
        }

        cursoRepository.save(curso);
        return "Curso '" + curso.getNombre() + "' creado correctamente.";
    }

    public List<CursoDTO> listarDTO() {
        return cursoRepository.findAll()
                              .stream()
                              .map(CursoDTO::new)
                              .toList();
    }
    
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public String actualizar(Curso curso) {
        Curso existente = cursoRepository.findById(curso.getId()).orElse(null);

        if (existente == null) {
            return "Curso no encontrado.";
        }

        existente.setNombre(curso.getNombre());
        existente.setPrecio(curso.getPrecio());
        cursoRepository.save(existente);
        return "Curso actualizado correctamente.";
    }

    public String eliminar(Integer id, String correo) {
        try {
            Curso curso = cursoRepository.findById(id).orElse(null);
            if (curso == null) return "Curso no encontrado.";

            Usuario usuario = usuarioRepository.findByCorreo(correo);
            if (usuario == null) return "Usuario no encontrado.";

            boolean esAdmin = usuario.getPerfiles().stream()
                .anyMatch(p -> "Administrador".equalsIgnoreCase(p.getNombre()));

            if (!esAdmin) return "No tienes permisos para eliminar cursos.";

            for (Instructor instructor : curso.getInstructores()) {
                instructor.getCursos().remove(curso);
            }

            for (Usuario u : curso.getUsuarios()) {
                u.getCursos().remove(curso);
            }

            curso.getInstructores().clear();
            curso.getUsuarios().clear();

            cursoRepository.saveAndFlush(curso);

            cursoRepository.delete(curso); 
            return "Curso eliminado correctamente.";

        } catch (Exception e) {
            return "Error al intentar eliminar el curso: " + e.getMessage();
        }
    }

    public String asignarCursoUsuario(String correoUsuario, Integer idCurso) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(correoUsuario);
        Optional<Curso> optCurso = cursoRepository.findById(idCurso);

        if (optUsuario.isEmpty()) {
            return "Usuario con correo '" + correoUsuario + "' no encontrado.";
        }

        if (optCurso.isEmpty()) {
            return "Curso con ID " + idCurso + " no encontrado.";
        }

        Usuario usuario = optUsuario.get();
        Curso curso = optCurso.get();

        if (usuario.getCursos() == null) {
            usuario.setCursos(new ArrayList<>());
        }

        if (usuario.getCursos().contains(curso)) {
            return "El curso ya está asignado al usuario.";
        }

        usuario.getCursos().add(curso);
        usuarioRepository.save(usuario);

        return "Curso '" + curso.getNombre() + "' asignado correctamente al usuario '" + correoUsuario;
    }

    public String asignarCursoInstructor(String rutInstructor, Integer idCurso) {
        Optional<Instructor> optInstructor = instructorRepository.findById(rutInstructor);
        Optional<Curso> optCurso = cursoRepository.findById(idCurso);

        if (optInstructor.isEmpty()) {
            return "Instructor con RUT '" + rutInstructor + "' no encontrado.";
        }

        if (optCurso.isEmpty()) {
            return "Curso con ID " + idCurso + " no encontrado.";
        }

        Instructor instructor = optInstructor.get();
        Curso curso = optCurso.get();

        if (instructor.getCursos() == null) {
            instructor.setCursos(new ArrayList<>());
        }

        if (instructor.getCursos().contains(curso)) {
            return "El curso ya está asignado al instructor.";
        }

        instructor.getCursos().add(curso);
        instructorRepository.save(instructor);

        return "Curso '" + curso.getNombre() + "' asignado correctamente al instructor '" + rutInstructor + "'.";
    }

    public List<Curso> buscar(String nombre) {
        return cursoRepository.findByNombreContaining(nombre);
    }

}
