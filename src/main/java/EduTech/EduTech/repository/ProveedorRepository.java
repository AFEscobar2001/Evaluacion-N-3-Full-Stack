package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import EduTech.EduTech.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    Proveedor findByNombre(String nombre);
}
