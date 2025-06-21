package EduTech.EduTech.controller;

import EduTech.EduTech.dto.InstructorDTO;
import EduTech.EduTech.model.Instructor;
import EduTech.EduTech.service.InstructorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public String almacenar(@RequestBody Instructor instructor) {
        return instructorService.almacenar(instructor);
    }

    @GetMapping
    public ResponseEntity<List<InstructorDTO>> listarDTO() {
        return ResponseEntity.ok(instructorService.listarDTO());
    }

    @DeleteMapping("/{rut}/cursos/{idCurso}")
    public String eliminarCurso(@PathVariable String rut, @PathVariable Integer idCurso) {
        return instructorService.eliminar(rut, idCurso);
    }
}
