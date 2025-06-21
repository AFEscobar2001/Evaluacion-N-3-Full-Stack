package EduTech.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.EduTech.dto.IncidenciaDTO;
import EduTech.EduTech.model.Incidencia;
import EduTech.EduTech.service.IncidenciaService;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping
    public String almacenar(@RequestBody Incidencia incidencia) {
        return incidenciaService.almacenar(incidencia);
    }

    @GetMapping
    public List<IncidenciaDTO> listarDTO() {
        return incidenciaService.listarDTO();
    }

}
