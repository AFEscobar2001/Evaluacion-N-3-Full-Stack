package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import EduTech.EduTech.model.CuponDescuento;
import EduTech.EduTech.repository.CuponDescuentoRepository;
import EduTech.EduTech.service.CuponDescuentoService;

@ExtendWith(MockitoExtension.class)
public class CuponDescuentoServiceTest {

    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @InjectMocks
    private CuponDescuentoService cuponDescuentoService;

    @Test
    void almacenar_cuponValido() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setCodigo("DESC10");
        cupon.setDescuento(10);
        cupon.setActivo(true);

        when(cuponDescuentoRepository.findByCodigo("DESC10")).thenReturn(null);

        String resultado = cuponDescuentoService.almacenar(cupon);

        assertEquals("Cupón registrado correctamente.", resultado);
        verify(cuponDescuentoRepository).save(cupon);
    }

    @Test
    void almacenar_codigoVacio() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setCodigo("");
        cupon.setDescuento(10);

        String resultado = cuponDescuentoService.almacenar(cupon);

        assertEquals("El código del cupón es obligatorio.", resultado);
        verify(cuponDescuentoRepository, never()).save(any());
    }

    @Test
    void almacenar_descuentoInvalido() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setCodigo("DESC0");
        cupon.setDescuento(0);

        String resultado = cuponDescuentoService.almacenar(cupon);

        assertEquals("El descuento debe ser mayor a 0.", resultado);
        verify(cuponDescuentoRepository, never()).save(any());
    }

    @Test
    void almacenar_codigoYaExiste() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setCodigo("DESC10");
        cupon.setDescuento(15);

        when(cuponDescuentoRepository.findByCodigo("DESC10")).thenReturn(new CuponDescuento());

        String resultado = cuponDescuentoService.almacenar(cupon);

        assertEquals("Ya existe un cupón con el código 'DESC10'.", resultado);
        verify(cuponDescuentoRepository, never()).save(any());
    }

    @Test
    void aplicarCupon_exitoso() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setCodigo("DESC20");
        cupon.setDescuento(20);
        cupon.setActivo(true); 

        when(cuponDescuentoRepository.findByCodigo("DESC20")).thenReturn(cupon);

        String resultado = cuponDescuentoService.aplicarCupon("DESC20");

        assertEquals("Cupón aplicado con éxito: 20.0 de descuento.", resultado);
        verify(cuponDescuentoRepository).findByCodigo("DESC20");
    }

    @Test
    void aplicarCupon_invalido() {
        when(cuponDescuentoRepository.findByCodigo("DESC20")).thenReturn(null);

        String resultado = cuponDescuentoService.aplicarCupon("DESC20");

        assertEquals("El cupón no existe o está inactivo.", resultado);
    }

    @Test
    void listar_todosLosCupones() {
        CuponDescuento c1 = new CuponDescuento();
        c1.setCodigo("DESC10");

        CuponDescuento c2 = new CuponDescuento();
        c2.setCodigo("DESC20");

        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(c1, c2));

        List<CuponDescuento> resultado = cuponDescuentoService.listar();

        assertEquals(2, resultado.size());
        assertEquals("DESC10", resultado.get(0).getCodigo());
        assertEquals("DESC20", resultado.get(1).getCodigo());
    }
    
}
