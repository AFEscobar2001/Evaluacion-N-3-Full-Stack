package EduTech.EduTech.controller;

import EduTech.EduTech.dto.PerfilDTO;
import EduTech.EduTech.model.Perfil;
import EduTech.EduTech.service.PerfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public String almacenar(@RequestBody Perfil perfil) {
        return perfilService.almacenar(perfil);
    }

    @GetMapping
    public List<PerfilDTO> listarDTO() {
        return perfilService.listarDTO();
    }

    @PutMapping("/{correo}/perfil/{id}")
    public String asignarPerfil(@PathVariable String correo, @PathVariable Integer id) {
        return perfilService.asignarPerfil(correo, id);
    }

}
