package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;

import EduTech.EduTech.model.Contenido;
import EduTech.EduTech.model.Evaluacion;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.ContenidoRepository;
import EduTech.EduTech.repository.EvaluacionRepository;
import EduTech.EduTech.repository.UsuarioRepository;
import EduTech.EduTech.service.EvaluacionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @Mock
    private ContenidoRepository contenidoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;


    @Test
    void guardarNotaFueraDeRangoInferior() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNota(0.5);
        String resultado = evaluacionService.almacenar(evaluacion);
        assertEquals("La nota debe estar entre 1.0 y 7.0.", resultado);
    }

    @Test
    void guardarNotaFueraDeRangoSuperior() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNota(7.5);
        String resultado = evaluacionService.almacenar(evaluacion);
        assertEquals("La nota debe estar entre 1.0 y 7.0.", resultado);
    }

    @Test
    void guardarContenidoNoValido() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNota(5.5);

        Contenido contenido = new Contenido();
        contenido.setId(1);
        evaluacion.setContenido(contenido);

        evaluacion.setUsuario(new Usuario());

        when(contenidoRepository.existsById(contenido.getId())).thenReturn(false);

        String resultado = evaluacionService.almacenar(evaluacion);
        assertEquals("Debe asociar la evaluación a un contenido válido.", resultado);
    }

    @Test
    void guardarUsuarioNoValido() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNota(5.5);

        Contenido contenido = new Contenido();
        contenido.setId(1);
        evaluacion.setContenido(contenido);

        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duoc.cl");
        evaluacion.setUsuario(usuario);

        when(contenidoRepository.existsById(contenido.getId())).thenReturn(true);
        when(usuarioRepository.existsById(usuario.getCorreo())).thenReturn(false);

        String resultado = evaluacionService.almacenar(evaluacion);
        assertEquals("Debe asociar la evaluación a un usuario válido.", resultado);
    }

    @Test
    void guardarEvaluacionCorrectamente() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNota(5.5);
        evaluacion.setObservacion("Muy buen trabajo");

        Contenido contenido = new Contenido();
        contenido.setId(1);
        evaluacion.setContenido(contenido);

        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duoc.cl");
        evaluacion.setUsuario(usuario);

        when(contenidoRepository.existsById(contenido.getId())).thenReturn(true);
        when(usuarioRepository.existsById(usuario.getCorreo())).thenReturn(true);
        when(evaluacionRepository.save(any(Evaluacion.class))).thenReturn(evaluacion);

        String resultado = evaluacionService.almacenar(evaluacion);
        assertEquals("Evaluación registrada correctamente.", resultado);
    }

    @Test
    void listarEvaluaciones() {
        Evaluacion e1 = new Evaluacion();
        e1.setId(1);
        e1.setNota(5.5);
        e1.setObservacion("Muy bien");

        Evaluacion e2 = new Evaluacion();
        e2.setId(2);
        e2.setNota(6.5);
        e2.setObservacion("Excelente");

        List<Evaluacion> lista = new ArrayList<>();
        lista.add(e1);
        lista.add(e2);

        when(evaluacionRepository.findAll()).thenReturn(lista);

        List<Evaluacion> resultado = evaluacionRepository.findAll();

        assertEquals(2, resultado.size());

        List<Double> notas = resultado.stream().map(Evaluacion::getNota).toList();
        assertTrue(notas.contains(5.5));
        assertTrue(notas.contains(6.5));
    }
    
}
