package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import EduTech.EduTech.dto.ContenidoDTO;
import EduTech.EduTech.model.Contenido;
import EduTech.EduTech.model.Curso;
import EduTech.EduTech.model.Proveedor;
import EduTech.EduTech.repository.ContenidoRepository;
import EduTech.EduTech.repository.CursoRepository;
import EduTech.EduTech.repository.ProveedorRepository;
import EduTech.EduTech.service.ContenidoService;

@ExtendWith(MockitoExtension.class)
public class ContenidoServiceTest {

    @Mock
    private ContenidoRepository contenidoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ContenidoService contenidoService;

    @Test
    void guardarContenido() {
        Curso curso = new Curso();
        curso.setId(1); 
        curso.setNombre("Semestre 1");

        Proveedor proveedor = new Proveedor();
        proveedor.setId(1); 

        Contenido contenido = new Contenido();
        contenido.setTitulo("Full Stack 1");
        contenido.setDescripcion("Java");
        contenido.setCurso(curso);
        contenido.setProveedor(proveedor);

        when(cursoRepository.existsById(curso.getId())).thenReturn(true); 
        when(proveedorRepository.existsById(proveedor.getId())).thenReturn(true); 
        when(contenidoRepository.findByTitulo("Full Stack 1")).thenReturn(null); 
        when(contenidoRepository.save(contenido)).thenReturn(contenido);

        String resultado = contenidoService.almacenar(contenido);

        assertEquals("Contenido 'Full Stack 1' guardado correctamente.", resultado);
        verify(contenidoRepository).save(contenido);
    }

    @Test
    void listarContenidoDTO() {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Programación");

        Proveedor proveedor = new Proveedor();
        proveedor.setId(1);
        proveedor.setNombre("Chile SA");

        Contenido c1 = new Contenido();
        c1.setTitulo("Semestre 1");
        c1.setDescripcion("Java");
        c1.setCurso(curso);
        c1.setProveedor(proveedor);

        Contenido c2 = new Contenido();
        c2.setTitulo("Semestre 2");
        c2.setDescripcion("Python");
        c2.setCurso(curso);
        c2.setProveedor(proveedor);

        List<Contenido> lista = List.of(c1, c2);

        when(contenidoRepository.findAll()).thenReturn(lista);

        List<ContenidoDTO> resultado = contenidoService.listarDTO();

        assertEquals(2, resultado.size());
        assertEquals("Semestre 1", resultado.get(0).getTitulo());
        assertEquals("Semestre 2", resultado.get(1).getTitulo());
    }

}
