package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import EduTech.EduTech.model.CuponDescuento;

public interface CuponDescuentoRepository extends JpaRepository<CuponDescuento, String> {

    CuponDescuento findByCodigo(String codigo);

}
