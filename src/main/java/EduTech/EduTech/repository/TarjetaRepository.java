package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import EduTech.EduTech.model.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
    Tarjeta findByNumero(String titular);

}
