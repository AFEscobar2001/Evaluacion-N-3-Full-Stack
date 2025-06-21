package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import EduTech.EduTech.dto.PersonaDTO;
import EduTech.EduTech.model.Persona;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.PersonaRepository;
import EduTech.EduTech.repository.UsuarioRepository;
import EduTech.EduTech.service.PersonaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private PersonaService personaService;

    @Test
    void guardarPersonaUsuarioNoExiste() {
        PersonaDTO dto = new PersonaDTO();
        dto.setCorreo("usuario@duocuc.cl");
        dto.setRut("12345678-9");
        dto.setNombre("Felipe");
        dto.setApellido("Escobar");

        when(usuarioRepository.findById(dto.getCorreo())).thenReturn(Optional.empty());

        String resultado = personaService.almacenar(dto);

        assertEquals("No se puede registrar la persona. Primero debes crear un usuario con el correo proporcionado.", resultado);
    }

    @Test
    void guardarPersonaYaRegistrada() {
        PersonaDTO dto = new PersonaDTO();
        dto.setCorreo("usuario@duocuc.cl");
        dto.setRut("12345678-9");
        dto.setNombre("Felipe");
        dto.setApellido("Escobar");

        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());

        when(usuarioRepository.findById(dto.getCorreo())).thenReturn(Optional.of(usuario));
        when(personaRepository.existsById(dto.getRut())).thenReturn(true);

        String resultado = personaService.almacenar(dto);

        assertEquals("La persona con RUT 12345678-9 ya está registrada.", resultado);
    }

    @Test
    void guardarPersonaCorrectamente() {
        PersonaDTO dto = new PersonaDTO();
        dto.setCorreo("usuario@duocuc.cl");
        dto.setRut("12345678-9");
        dto.setNombre("Felipe");
        dto.setApellido("Escobar");

        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());

        when(usuarioRepository.findById(dto.getCorreo())).thenReturn(Optional.of(usuario));
        when(personaRepository.existsById(dto.getRut())).thenReturn(false);
        when(personaRepository.save(any(Persona.class))).thenReturn(new Persona());

        String resultado = personaService.almacenar(dto);

        assertEquals("Persona registrada correctamente.", resultado);
    }

    @Test
    void listarPersonas() {
        Persona p1 = new Persona();
        p1.setRut("11111111-1");
        p1.setNombre("Felipe");
        p1.setApellido("Escobar");

        Persona p2 = new Persona();
        p2.setRut("22222222-2");
        p2.setNombre("Alison");
        p2.setApellido("Aranda");

        List<Persona> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);

        when(personaRepository.findAll()).thenReturn(lista);

        List<Persona> resultado = personaService.listar();

        assertEquals(2, resultado.size());

        List<String> ruts = resultado.stream().map(Persona::getRut).toList();
        assertTrue(ruts.contains("11111111-1"));
        assertTrue(ruts.contains("22222222-2"));
    }
    
}
