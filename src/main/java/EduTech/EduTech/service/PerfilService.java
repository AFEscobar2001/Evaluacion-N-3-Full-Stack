package EduTech.EduTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduTech.EduTech.dto.PerfilDTO;
import EduTech.EduTech.model.Perfil;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.PerfilRepository;
import EduTech.EduTech.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Realizado por: Alison Aranda

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired UsuarioRepository usuarioRepository;

    public String almacenar(Perfil perfil) {
        Perfil validacion = perfilRepository.findByNombre(perfil.getNombre());

            if (validacion == null) {
                perfilRepository.save(perfil);
                return "Perfil " + perfil.getNombre() + " creado correctamente.";
            } else {
                return "El perfil " + perfil.getNombre() + " ya existe.";
            }
    }

    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }

    public List<PerfilDTO> listarDTO() {
        return perfilRepository.findAll()
                .stream()
                .map(PerfilDTO::new)
                .collect(Collectors.toList());
    }

    public String asignarPerfil(String correoUsuario, Integer idPerfil) {
    Optional<Usuario> optUsuario = usuarioRepository.findById(correoUsuario);
    Optional<Perfil> optPerfil = perfilRepository.findById(idPerfil);

        if (optUsuario.isEmpty()) {
            return "Usuario con correo '" + correoUsuario + "' no encontrado.";
        }

        if (optPerfil.isEmpty()) {
            return "Perfil con ID " + idPerfil + " no encontrado.";
        }

        Usuario usuario = optUsuario.get();
        Perfil perfil = optPerfil.get();

        if (usuario.getPerfiles().contains(perfil)) {
            return "El usuario ya tiene asignado el perfil '" + perfil.getNombre() + "'.";
        }

        usuario.getPerfiles().add(perfil);
        usuarioRepository.save(usuario);

        return "Perfil '" + perfil.getNombre() + "' asignado correctamente al usuario '" + correoUsuario + "'.";
    }

}
