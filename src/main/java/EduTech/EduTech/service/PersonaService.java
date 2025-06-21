package EduTech.EduTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduTech.EduTech.dto.PersonaDTO;
import EduTech.EduTech.model.Persona;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.PersonaRepository;
import EduTech.EduTech.repository.UsuarioRepository;

import java.util.List;

//Realizado por: Alison Aranda

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String almacenar(PersonaDTO personaDTO) {
        if (personaDTO.getCorreo() == null || personaDTO.getCorreo().isBlank()) {
            return "El correo del usuario es obligatorio.";
        }

        if (personaDTO.getRut() == null || personaDTO.getRut().isBlank()) {
            return "El RUT es obligatorio.";
        }

        Usuario usuario = usuarioRepository.findById(personaDTO.getCorreo()).orElse(null);
        if (usuario == null) {
            return "No se puede registrar la persona. Primero debes crear un usuario con el correo proporcionado.";
        }

        if (personaRepository.existsById(personaDTO.getRut())) {
            return "La persona con RUT " + personaDTO.getRut() + " ya está registrada.";
        }

        Persona persona = new Persona();
        persona.setRut(personaDTO.getRut());
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setUsuario(usuario);

        personaRepository.save(persona);
        return "Persona registrada correctamente.";
    }

    public List<Persona> listar() {
        return personaRepository.findAll();
    }

}
