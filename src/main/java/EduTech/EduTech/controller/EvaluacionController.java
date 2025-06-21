package EduTech.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.EduTech.dto.EvaluacionDTO;
import EduTech.EduTech.model.Evaluacion;
import EduTech.EduTech.service.EvaluacionService;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {
    @Autowired
    private EvaluacionService evaluacionService;

    @PostMapping
    public String almacenar(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.almacenar(evaluacion);
    }

    @GetMapping
    public List<EvaluacionDTO> listarEvaluaciones() {
        return evaluacionService.listarDTO();
    }
}
