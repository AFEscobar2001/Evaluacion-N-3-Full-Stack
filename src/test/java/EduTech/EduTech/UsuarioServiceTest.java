package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import EduTech.EduTech.dto.UsuarioDTO;
import EduTech.EduTech.model.Perfil;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.UsuarioRepository;
import EduTech.EduTech.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void almacenarUsuarioNuevo() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("ali.aranda@duocuc");
        usuario.setContrasena("123456");

        when(usuarioRepository.existsById(usuario.getCorreo())).thenReturn(false);

        String resultado = usuarioService.almacenar(usuario);

        assertEquals("Usuario ali.aranda@duocuc creado correctamente.", resultado);
    }

    @Test
    void listarUsuarios() {
        Usuario u1 = new Usuario();
        u1.setCorreo("ali.aranda@duocuc.cl");
        u1.setContrasena("123456");
        u1.setPerfiles(new ArrayList<>());

        Usuario u2 = new Usuario();
        u2.setCorreo("andres@duocuc.cl");
        u2.setContrasena("159357");
        u2.setPerfiles(new ArrayList<>());

        List<Usuario> lista = new ArrayList<>();
        lista.add(u1);
        lista.add(u2);

        when(usuarioRepository.findAll()).thenReturn(lista);

        List<UsuarioDTO> resultado = usuarioService.listarDTO();

        assertEquals(2, resultado.size());

        List<String> correos = resultado.stream().map(UsuarioDTO::getCorreo).toList();
        assertTrue(correos.contains("ali.aranda@duocuc.cl"));
        assertTrue(correos.contains("andres@duocuc.cl"));
    }

    @Test
    void actualizarUsuario() {
        Usuario existente = new Usuario();
        existente.setCorreo("ali.aranda@duocuc.cl");
        existente.setContrasena("123456");
        existente.setEstado(true);

        Usuario actualizado = new Usuario();
        actualizado.setCorreo("ali.aranda@duocuc.cl");
        actualizado.setContrasena("987654");
        actualizado.setEstado(false);

        when(usuarioRepository.findById(actualizado.getCorreo())).thenReturn(Optional.of(existente));

        String resultado = usuarioService.actualizar(actualizado);

        assertEquals("Usuario actualizado correctamente.", resultado);
    }

    @Test
    void noTienePermisosParaEliminar() {
        String correoAEliminar = "ali.aranda@duocuc.cl";
        String correoSolicitante = "andres@duocuc.cl";

        Usuario solicitante = new Usuario();
        solicitante.setCorreo(correoSolicitante);
        solicitante.setPerfiles(new ArrayList<>());

        when(usuarioRepository.findById(correoSolicitante)).thenReturn(Optional.of(solicitante));

        String resultado = usuarioService.eliminar(correoAEliminar, correoSolicitante);

        assertEquals("No tienes permisos para eliminar usuarios.", resultado);
    }

    @Test
    void eliminarUsuario() {
        String correoAEliminar = "ali.aranda@duocuc.cl";
        String correoSolicitante = "andres@duocuc.cl";

        Usuario solicitante = new Usuario();
        solicitante.setCorreo(correoSolicitante);

        Perfil perfilAdmin = new Perfil();
        perfilAdmin.setNombre("Administrador");

        solicitante.setPerfiles(List.of(perfilAdmin));

        Usuario usuarioAEliminar = new Usuario();
        usuarioAEliminar.setCorreo(correoAEliminar);
        usuarioAEliminar.setCursos(new ArrayList<>());
        usuarioAEliminar.setPerfiles(new ArrayList<>());

        when(usuarioRepository.findById(correoSolicitante)).thenReturn(Optional.of(solicitante));
        when(usuarioRepository.findById(correoAEliminar)).thenReturn(Optional.of(usuarioAEliminar));

        String resultado = usuarioService.eliminar(correoAEliminar, correoSolicitante);

        assertEquals("Usuario eliminado correctamente.", resultado);
    }

}
