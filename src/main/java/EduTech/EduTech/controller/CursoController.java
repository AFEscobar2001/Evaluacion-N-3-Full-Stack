package EduTech.EduTech.controller;

import EduTech.EduTech.dto.CursoDTO;
import EduTech.EduTech.model.Curso;
import EduTech.EduTech.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public String almacenar(@RequestBody Curso curso) {
        return cursoService.almacenar(curso);
    }

    @GetMapping
    public List<CursoDTO> listarDTO() {
        return cursoService.listarDTO();
    }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.actualizar(curso);
    }

    @DeleteMapping("/{id}/eliminar/{correo}")
    public String eliminar(@PathVariable Integer id, @PathVariable String correo) {
        return cursoService.eliminar(id, correo);
    }

    @PutMapping("/{correo}/usuarios/{idCurso}")
    public String asignarCursoUsuario(@PathVariable String correo, @PathVariable Integer idCurso) {
        return cursoService.asignarCursoUsuario(correo, idCurso);
    }

    @PutMapping("/{rut}/instructores/{idCurso}")
    public String asignarCursoInstructor(@PathVariable String rut, @PathVariable Integer idCurso) {
        return cursoService.asignarCursoInstructor(rut, idCurso);
    }

    @GetMapping("/buscar")
    public List<Curso> buscarPorNombre(@RequestParam String nombre) {
        return cursoService.buscar(nombre);
    }

}
