package EduTech.EduTech;

import EduTech.EduTech.dto.InstructorDTO;
import EduTech.EduTech.model.Curso;
import EduTech.EduTech.model.Instructor;
import EduTech.EduTech.repository.CursoRepository;
import EduTech.EduTech.repository.InstructorRepository;
import EduTech.EduTech.service.InstructorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private InstructorService instructorService;

    @Test
    void almacenar_instructorValido() {
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");
        instructor.setCorreo("instructor@correo.com");
        instructor.setContrasena("1234");
        instructor.setNombre("Felipe");
        instructor.setApellido("Escobar");

        when(instructorRepository.existsById("12345678-9")).thenReturn(false);

        String resultado = instructorService.almacenar(instructor);

        assertEquals("Instructor registrado correctamente.", resultado);
        verify(instructorRepository).save(instructor);
    }

    @Test
    void almacenar_rutVacio() {
        Instructor instructor = new Instructor();
        instructor.setRut(" ");
        String resultado = instructorService.almacenar(instructor);
        assertEquals("El RUT es obligatorio.", resultado);
    }

    @Test
    void almacenar_correoVacio() {
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");
        instructor.setCorreo(" ");
        String resultado = instructorService.almacenar(instructor);
        assertEquals("El correo es obligatorio.", resultado);
    }

    @Test
    void almacenar_contrasenaVacia() {
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");
        instructor.setCorreo("instructor@duoc.cl");
        instructor.setContrasena(" ");
        String resultado = instructorService.almacenar(instructor);
        assertEquals("La contraseña es obligatoria.", resultado);
    }

    @Test
    void almacenar_nombreApellidoVacios() {
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");
        instructor.setCorreo("instructor@duoc.cl");
        instructor.setContrasena("159357");
        instructor.setNombre(" ");
        instructor.setApellido(" ");
        String resultado = instructorService.almacenar(instructor);
        assertEquals("El nombre y apellido son obligatorios.", resultado);
    }

    @Test
    void almacenar_instructorDuplicado() {
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");
        instructor.setCorreo("instructor@duoc.cl");
        instructor.setContrasena("159357");
        instructor.setNombre("Felipe");
        instructor.setApellido("Escobar");

        when(instructorRepository.existsById("12345678-9")).thenReturn(true);

        String resultado = instructorService.almacenar(instructor);
        assertEquals("Ya existe un instructor registrado con ese RUT.", resultado);
    }

    @Test
    void listarDTO_devuelveLista() {
        Instructor i1 = new Instructor();
        i1.setNombre("Felipe");
        Instructor i2 = new Instructor();
        i2.setNombre("Cristina");

        when(instructorRepository.findAll()).thenReturn(List.of(i1, i2));

        List<InstructorDTO> resultado = instructorService.listarDTO();

        assertEquals(2, resultado.size());
        assertEquals("Felipe", resultado.get(0).getNombre());
        assertEquals("Cristina", resultado.get(1).getNombre());
    }

    @Test
    void eliminar_cursoAsignado_correcto() {
        String rut = "12345678-9";
        Integer idCurso = 1;

        Curso curso = new Curso();
        curso.setId(idCurso);

        Instructor instructor = new Instructor();
        instructor.setRut(rut);
        instructor.setCursos(new ArrayList<>(List.of(curso)));

        when(instructorRepository.findById(rut)).thenReturn(Optional.of(instructor));
        when(cursoRepository.findById(idCurso)).thenReturn(Optional.of(curso));

        String resultado = instructorService.eliminar(rut, idCurso);

        assertEquals("Curso eliminado del instructor correctamente.", resultado);
        verify(instructorRepository).save(instructor);
    }

    @Test
    void eliminar_instructorNoExiste() {
        when(instructorRepository.findById("12345678-9")).thenReturn(Optional.empty());

        String resultado = instructorService.eliminar("12345678-9", 1);

        assertEquals("Instructor no encontrado.", resultado);
    }

    @Test
    void eliminar_cursoNoExiste() {
        Instructor instructor = new Instructor();
        instructor.setRut("12345678-9");

        when(instructorRepository.findById("12345678-9")).thenReturn(Optional.of(instructor));
        when(cursoRepository.findById(1)).thenReturn(Optional.empty());

        String resultado = instructorService.eliminar("12345678-9", 1);

        assertEquals("Curso no encontrado.", resultado);
    }

    @Test
    void eliminar_cursoNoAsignado() {
        String rut = "12345678-9";
        Integer idCurso = 1;

        Curso curso = new Curso();
        curso.setId(idCurso);

        Instructor instructor = new Instructor();
        instructor.setRut(rut);
        instructor.setCursos(new ArrayList<>());

        when(instructorRepository.findById(rut)).thenReturn(Optional.of(instructor));
        when(cursoRepository.findById(idCurso)).thenReturn(Optional.of(curso));

        String resultado = instructorService.eliminar(rut, idCurso);

        assertEquals("El instructor no tiene asignado ese curso.", resultado);
    }

}
