package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import EduTech.EduTech.model.FormaPago;
import EduTech.EduTech.repository.FormaPagoRepository;
import EduTech.EduTech.service.FormaPagoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FormaPagoServiceTest {

    @Mock
    private FormaPagoRepository formaPagoRepository;

    @InjectMocks
    private FormaPagoService formaPagoService;

    @Test
    void guardarFormaPagoYaExiste() {
        FormaPago formaPago = new FormaPago();
        formaPago.setNombre("Crédito");

        when(formaPagoRepository.findByNombre(formaPago.getNombre())).thenReturn(formaPago);

        String resultado = formaPagoService.almacenar(formaPago);

        assertEquals("Ya existe una forma de pago con ese nombre.", resultado);
    }

    @Test
    void guardarFormaPagoCorrectamente() {
        FormaPago formaPago = new FormaPago();
        formaPago.setNombre("Crédito");

        when(formaPagoRepository.findByNombre(formaPago.getNombre())).thenReturn(null);
        when(formaPagoRepository.save(any(FormaPago.class))).thenReturn(formaPago);

        String resultado = formaPagoService.almacenar(formaPago);

        assertEquals("Forma de pago 'Crédito' registrada correctamente.", resultado);
    }

    @Test
    void listarFormasPago() {
        FormaPago f1 = new FormaPago();
        f1.setId(1);
        f1.setNombre("Crédito");

        FormaPago f2 = new FormaPago();
        f2.setId(2);
        f2.setNombre("Débito");

        List<FormaPago> lista = new ArrayList<>();
        lista.add(f1);
        lista.add(f2);

        when(formaPagoRepository.findAll()).thenReturn(lista);

        List<FormaPago> resultado = formaPagoService.listar();

        assertEquals(2, resultado.size());

        List<String> nombres = resultado.stream().map(FormaPago::getNombre).toList();
        assertTrue(nombres.contains("Crédito"));
        assertTrue(nombres.contains("Débito"));
    }

}
