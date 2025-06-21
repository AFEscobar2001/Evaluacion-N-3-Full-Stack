package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import EduTech.EduTech.model.FormaPago;
import EduTech.EduTech.model.Tarjeta;
import EduTech.EduTech.model.Usuario;
import EduTech.EduTech.repository.FormaPagoRepository;
import EduTech.EduTech.repository.TarjetaRepository;
import EduTech.EduTech.repository.UsuarioRepository;
import EduTech.EduTech.service.TarjetaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TarjetaServiceTest {

    @Mock
    private TarjetaRepository tarjetaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private FormaPagoRepository formaPagoRepository;

    @InjectMocks
    private TarjetaService tarjetaService;

    @Test
    void guardarUsuarioNoExiste() {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumero("123456789");
        tarjeta.setBanco("Banco Estado");
        tarjeta.setVencimiento("12/2025");

        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duocuc.cl");
        tarjeta.setUsuario(usuario);

        when(usuarioRepository.existsById(usuario.getCorreo())).thenReturn(false);

        String resultado = tarjetaService.almacenar(tarjeta);
        assertEquals("Debe asociar la tarjeta a un usuario válido.", resultado);
    }

    @Test
    void guardarTarjetaYaExiste() {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumero("123456789");
        tarjeta.setBanco("Banco Estado");
        tarjeta.setVencimiento("12/2025");

        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duocuc.cl");
        tarjeta.setUsuario(usuario);

        FormaPago formaPago = new FormaPago();
        formaPago.setId(1);
        tarjeta.setFormaPago(formaPago);

        when(usuarioRepository.existsById(usuario.getCorreo())).thenReturn(true);
        when(formaPagoRepository.existsById(formaPago.getId())).thenReturn(true);
        when(tarjetaRepository.findByNumero(tarjeta.getNumero())).thenReturn(tarjeta);

        String resultado = tarjetaService.almacenar(tarjeta);
        assertEquals("Ya existe una tarjeta registrada con ese número.", resultado);
    }

    @Test
    void guardarTarjetaCorrectamente() {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumero("123456789");
        tarjeta.setBanco("Banco Estado");
        tarjeta.setVencimiento("12/2025");

        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@duocuc.cl");
        tarjeta.setUsuario(usuario);

        FormaPago formaPago = new FormaPago();
        formaPago.setId(1);
        tarjeta.setFormaPago(formaPago);

        when(usuarioRepository.existsById(usuario.getCorreo())).thenReturn(true);
        when(formaPagoRepository.existsById(formaPago.getId())).thenReturn(true);
        when(tarjetaRepository.findByNumero(tarjeta.getNumero())).thenReturn(null);
        when(tarjetaRepository.save(any(Tarjeta.class))).thenReturn(tarjeta);

        String resultado = tarjetaService.almacenar(tarjeta);
        assertEquals("Tarjeta registrada correctamente.", resultado);
    }

    @Test
    void listarTarjetas() {
        Tarjeta t1 = new Tarjeta();
        t1.setId(1);
        t1.setNumero("123456789");
        t1.setBanco("Banco Estado");

        Tarjeta t2 = new Tarjeta();
        t2.setId(2);
        t2.setNumero("987654321");
        t2.setBanco("Banco de Chile");

        List<Tarjeta> lista = new ArrayList<>();
        lista.add(t1);
        lista.add(t2);

        when(tarjetaRepository.findAll()).thenReturn(lista);

        List<Tarjeta> resultado = tarjetaService.listar();

        assertEquals(2, resultado.size());

        List<String> numeros = resultado.stream().map(Tarjeta::getNumero).toList();
        assertTrue(numeros.contains("123456789"));
        assertTrue(numeros.contains("987654321"));
    }
    
}
