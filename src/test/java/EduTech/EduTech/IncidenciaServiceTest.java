package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import EduTech.EduTech.dto.IncidenciaDTO;
import EduTech.EduTech.model.Incidencia;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.IncidenciaRepository;
import EduTech.EduTech.repository.UsuarioRepository;
import EduTech.EduTech.service.IncidenciaService;

@ExtendWith(MockitoExtension.class)
public class IncidenciaServiceTest {

    @Mock
    private IncidenciaRepository incidenciaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private IncidenciaService incidenciaService;

    @Test
    void almacenar_incidenciaValida() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duoc.cl");

        Incidencia incidencia = new Incidencia();
        incidencia.setDetalle("Error al iniciar sesión");
        incidencia.setFecha("2025-06-21");
        incidencia.setUsuario(usuario);

        when(usuarioRepository.existsById("usuario@duoc.cl")).thenReturn(true);

        String resultado = incidenciaService.almacenar(incidencia);

        assertEquals("Incidencia registrada correctamente.", resultado);
        verify(incidenciaRepository).save(incidencia);
    }

    @Test
    void almacenar_sinDetalle() {
        Incidencia incidencia = new Incidencia();
        incidencia.setDetalle("");
        incidencia.setFecha("2025-06-21");

        String resultado = incidenciaService.almacenar(incidencia);

        assertEquals("El detalle de la incidencia es obligatorio.", resultado);
        verify(incidenciaRepository, never()).save(any());
    }

    @Test
    void almacenar_sinFecha() {
        Incidencia incidencia = new Incidencia();
        incidencia.setDetalle("Error técnico");
        incidencia.setFecha("");

        String resultado = incidenciaService.almacenar(incidencia);

        assertEquals("La fecha de la incidencia es obligatoria.", resultado);
        verify(incidenciaRepository, never()).save(any());
    }

    @Test
    void almacenar_usuarioInvalido() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duoc.cl");

        Incidencia incidencia = new Incidencia();
        incidencia.setDetalle("Error inesperado");
        incidencia.setFecha("2025-06-21");
        incidencia.setUsuario(usuario);

        when(usuarioRepository.existsById("usuario@duoc.cl")).thenReturn(false);

        String resultado = incidenciaService.almacenar(incidencia);

        assertEquals("Debe asociar la incidencia a un usuario válido.", resultado);
        verify(incidenciaRepository, never()).save(any());
    }

    @Test
    void listarDTO_devuelveListaDTOs() {
        Incidencia incidencia1 = new Incidencia();
        incidencia1.setDetalle("No puedo ver los cursos");
        incidencia1.setFecha("2025-06-21");

        Incidencia incidencia2 = new Incidencia();
        incidencia2.setDetalle("No me carga el inicio");
        incidencia2.setFecha("2025-06-20");

        when(incidenciaRepository.findAll()).thenReturn(List.of(incidencia1, incidencia2));

        List<IncidenciaDTO> resultado = incidenciaService.listarDTO();

        assertEquals(2, resultado.size());
        assertEquals("No puedo ver los cursos", resultado.get(0).getDetalle());
        assertEquals("No me carga el inicio", resultado.get(1).getDetalle());
    }

}
