package EduTech.EduTech;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import EduTech.EduTech.model.Proveedor;
import EduTech.EduTech.repository.ProveedorRepository;
import EduTech.EduTech.service.ProveedorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void almacenar_proveedorValido() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Chile SA");

        when(proveedorRepository.findByNombre("Chile SA")).thenReturn(null);

        String resultado = proveedorService.almacenar(proveedor);

        assertEquals("Proveedor guardado correctamente", resultado);
        verify(proveedorRepository).save(proveedor);
    }

    @Test
    void almacenar_sinNombre() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("  ");

        String resultado = proveedorService.almacenar(proveedor);

        assertEquals("El nombre del proveedor es obligatorio", resultado);
        verify(proveedorRepository, never()).save(any());
    }

    @Test
    void almacenar_proveedorYaExiste() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor Existente");

        when(proveedorRepository.findByNombre("Proveedor Existente")).thenReturn(new Proveedor());

        String resultado = proveedorService.almacenar(proveedor);

        assertEquals("Ya existe un proveedor registrado con ese nombre", resultado);
        verify(proveedorRepository, never()).save(any());
    }

    @Test
    void listar_todosLosProveedores() {
        Proveedor p1 = new Proveedor();
        p1.setNombre("Chile SA");
        Proveedor p2 = new Proveedor();
        p2.setNombre("Columbia Cl");

        when(proveedorRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Proveedor> resultado = proveedorService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Chile SA", resultado.get(0).getNombre());
        assertEquals("Columbia Cl", resultado.get(1).getNombre());
    }

    @Test
    void buscarPorId_existente() {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(1);
        proveedor.setNombre("Chile SA");

        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        Proveedor resultado = proveedorService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Chile SA", resultado.getNombre());
    }

    @Test
    void buscarPorId_noExiste() {
        when(proveedorRepository.findById(10)).thenReturn(Optional.empty());

        Proveedor resultado = proveedorService.buscarPorId(10);

        assertNull(resultado);
    }

    @Test
    void eliminar_existente() {
        when(proveedorRepository.existsById(1)).thenReturn(true);

        String resultado = proveedorService.eliminar(1);

        assertEquals("Proveedor eliminado correctamente.", resultado);
        verify(proveedorRepository).deleteById(1);
    }

    @Test
    void eliminar_noExiste() {
        when(proveedorRepository.existsById(10)).thenReturn(false);

        String resultado = proveedorService.eliminar(10);

        assertEquals("El proveedor con ID 10 no existe.", resultado);
        verify(proveedorRepository, never()).deleteById(anyInt());
    }
}
