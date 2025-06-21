package EduTech.EduTech.controller;

import EduTech.EduTech.model.Proveedor;
import EduTech.EduTech.service.ProveedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public String almacenar(@RequestBody Proveedor proveedor) {
        return proveedorService.almacenar(proveedor);
    }

    @GetMapping
    public List<Proveedor> listar() {
        return proveedorService.listar();
    }

    @GetMapping("/{id}")
    public Proveedor buscarPorId(@PathVariable int id) {
        return proveedorService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return proveedorService.eliminar(id);
    }
    
}
