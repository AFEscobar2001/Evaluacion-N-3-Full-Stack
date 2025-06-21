package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import EduTech.EduTech.model.FormaPago;

public interface FormaPagoRepository extends JpaRepository<FormaPago, Integer> {
    FormaPago findByNombre(String nombre);

}
