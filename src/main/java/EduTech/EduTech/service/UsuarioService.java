package EduTech.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduTech.EduTech.dto.UsuarioDTO;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.UsuarioRepository;

//Realizado por: Alison Aranda

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String almacenar(Usuario usuario) {
        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            return "El correo es obligatorio.";
        }

        if (usuario.getContrasena() == null || usuario.getContrasena().isBlank()) {
            return "La contraseña es obligatoria.";
        }

        if (usuarioRepository.existsById(usuario.getCorreo())) {
            return "El usuario con correo " + usuario.getCorreo() + " ya existe.";
        }

        usuarioRepository.save(usuario);
        return "Usuario " + usuario.getCorreo() + " creado correctamente.";
    }

    public List<UsuarioDTO> listarDTO() {
        return usuarioRepository.findAll()
                                .stream()
                                .map(UsuarioDTO::new)
                                .toList();
    }

    public String actualizar(Usuario nuevoUsuario) {
        Usuario existente = usuarioRepository.findById(nuevoUsuario.getCorreo()).orElse(null);

        if (existente == null) {
            return "Usuario no encontrado.";
        }

        existente.setContrasena(nuevoUsuario.getContrasena());
        existente.setEstado(nuevoUsuario.isEstado());

        usuarioRepository.save(existente);
        return "Usuario actualizado correctamente.";
    }

    public String eliminar(String correoAEliminar, String correoSolicitante) {
        if (correoAEliminar.equalsIgnoreCase(correoSolicitante)) {
            return "No puedes eliminar tu propio usuario.";
        }

        Usuario solicitante = usuarioRepository.findById(correoSolicitante).orElse(null);
        if (solicitante == null || solicitante.getPerfiles().stream().noneMatch(p -> p.getNombre().equalsIgnoreCase("Administrador"))) {
            return "No tienes permisos para eliminar usuarios.";
        }

        Usuario usuario = usuarioRepository.findById(correoAEliminar).orElse(null);
        if (usuario == null) {
            return "Usuario no encontrado.";
        }
        
        usuario.getCursos().clear();
        usuario.getPerfiles().clear();

        usuarioRepository.delete(usuario);
        return "Usuario eliminado correctamente.";
    }

    public String desactivar(String correo) {
        Usuario usuario = usuarioRepository.findById(correo).orElse(null);

        if (usuario == null) {
            return "Usuario no encontrado.";
        }

        if (!usuario.isEstado()) {
            return "El usuario ya está desactivado.";
        }

        usuario.setEstado(false);
        usuarioRepository.save(usuario);
        return "Usuario desactivado correctamente.";
    }

}
