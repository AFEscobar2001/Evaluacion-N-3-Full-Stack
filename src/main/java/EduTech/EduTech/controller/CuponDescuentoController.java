package EduTech.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.EduTech.model.CuponDescuento;
import EduTech.EduTech.service.CuponDescuentoService;

@RestController
@RequestMapping("/cupones")
public class CuponDescuentoController {
    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @PostMapping
    public String almacenar(@RequestBody CuponDescuento cuponDescuento) {
        return cuponDescuentoService.almacenar(cuponDescuento);
    }

    @GetMapping
    public List<CuponDescuento> listar() {
        return cuponDescuentoService.listar();
    }

    @GetMapping("/aplicar/{codigo}")
    public String aplicarCupon(@PathVariable String codigo) {
        return cuponDescuentoService.aplicarCupon(codigo);
    }

}
