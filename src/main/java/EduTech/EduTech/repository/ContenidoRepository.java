package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import EduTech.EduTech.model.Contenido;

public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {

    Contenido findByTitulo(String titulo);
}
