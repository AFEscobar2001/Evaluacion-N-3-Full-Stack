package EduTech.EduTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import EduTech.EduTech.dto.PersonaDTO;

import EduTech.EduTech.service.PersonaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public String almacenar(@RequestBody PersonaDTO personaDTO) {
        return personaService.almacenar(personaDTO);
    }

    @GetMapping
    public List<PersonaDTO> listar() {
        return personaService.listar()
                .stream()
                .map(p -> {
                    PersonaDTO dto = new PersonaDTO();
                    dto.setRut(p.getRut());
                    dto.setNombre(p.getNombre());
                    dto.setApellido(p.getApellido());
                    dto.setCorreo(p.getUsuario().getCorreo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
