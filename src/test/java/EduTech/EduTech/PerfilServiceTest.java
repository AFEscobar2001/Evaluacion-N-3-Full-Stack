package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import EduTech.EduTech.model.Perfil;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.PerfilRepository;
import EduTech.EduTech.repository.UsuarioRepository;
import EduTech.EduTech.service.PerfilService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PerfilServiceTest {

    @Mock
    private PerfilRepository perfilRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private PerfilService perfilService;

    @Test
    void almacenarPerfil() {
        Perfil perfil = new Perfil();
        perfil.setNombre("Administrador");

        when(perfilRepository.findByNombre(perfil.getNombre())).thenReturn(null);

        String resultado = perfilService.almacenar(perfil);

        assertEquals("Perfil Administrador creado correctamente.", resultado);
    }

    @Test
    void listarPerfiles() {
        Perfil p1 = new Perfil();
        p1.setNombre("Administrador");

        Perfil p2 = new Perfil();
        p2.setNombre("Estudiante");

        List<Perfil> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);

        when(perfilRepository.findAll()).thenReturn(lista);

        List<Perfil> resultado = perfilService.listar();

        assertEquals(2, resultado.size());

        List<String> nombres = resultado.stream().map(Perfil::getNombre).toList();
        assertTrue(nombres.contains("Administrador"));
        assertTrue(nombres.contains("Estudiante"));
    }

    @Test
    void asignarPerfilYaAsignado() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duocuc.cl");

        Perfil perfil = new Perfil();
        perfil.setId(1);
        perfil.setNombre("Administrador");

        usuario.setPerfiles(List.of(perfil));

        when(usuarioRepository.findById("usuario@duocuc.cl")).thenReturn(Optional.of(usuario));
        when(perfilRepository.findById(1)).thenReturn(Optional.of(perfil));

        String resultado = perfilService.asignarPerfil("usuario@duocuc.cl", 1);

        assertEquals("El usuario ya tiene asignado el perfil 'Administrador'.", resultado);
    }

    @Test
    void asignarPerfilCorrectamente() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duocuc.cl");
        usuario.setPerfiles(new ArrayList<>());

        Perfil perfil = new Perfil();
        perfil.setId(1);
        perfil.setNombre("Administrador");

        when(usuarioRepository.findById("usuario@duocuc.cl")).thenReturn(Optional.of(usuario));
        when(perfilRepository.findById(1)).thenReturn(Optional.of(perfil));

        String resultado = perfilService.asignarPerfil("usuario@duocuc.cl", 1);

        assertEquals("Perfil 'Administrador' asignado correctamente al usuario 'usuario@duocuc.cl'.", resultado);
    }

}
