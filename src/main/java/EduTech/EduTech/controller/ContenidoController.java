package EduTech.EduTech.controller;

import EduTech.EduTech.dto.ContenidoDTO;
import EduTech.EduTech.model.Contenido;
import EduTech.EduTech.service.ContenidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @PostMapping
    public String almacenar(@RequestBody Contenido contenido) {
        return contenidoService.almacenar(contenido);
    }

    @GetMapping
    public List<ContenidoDTO> listar() {
        return contenidoService.listarDTO();
    }

}
