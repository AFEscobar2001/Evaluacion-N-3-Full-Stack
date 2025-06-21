package EduTech.EduTech.service;

import EduTech.EduTech.model.Proveedor;
import EduTech.EduTech.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Realizado por: Felipe Escobar

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public String almacenar(Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isBlank()) {
            return "El nombre del proveedor es obligatorio";
        }

        Proveedor existente = proveedorRepository.findByNombre(proveedor.getNombre());
        if (existente != null) {
            return "Ya existe un proveedor registrado con ese nombre";
        }

        proveedorRepository.save(proveedor);
        return "Proveedor guardado correctamente";
    }

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public Proveedor buscarPorId(int id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public String eliminar(int id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return "Proveedor eliminado correctamente.";
        } else {
            return "El proveedor con ID " + id + " no existe.";
        }
    }

}
