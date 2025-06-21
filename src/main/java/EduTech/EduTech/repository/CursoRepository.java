package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import EduTech.EduTech.model.Curso;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    Curso findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    List<Curso> findByNombreContaining(String nombre);

}
