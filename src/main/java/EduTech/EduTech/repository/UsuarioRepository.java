package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import EduTech.EduTech.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

    Usuario findByCorreo(String correo);
    
}
