package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import EduTech.EduTech.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Perfil findByNombre(String nombre); 

}
