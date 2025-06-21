package EduTech.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.EduTech.dto.TarjetaDTO;
import EduTech.EduTech.model.Tarjeta;
import EduTech.EduTech.service.TarjetaService;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {
    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping
    public String almacenar(@RequestBody Tarjeta tarjeta) {
        return tarjetaService.almacenar(tarjeta);
    }

    @GetMapping
    public List<TarjetaDTO> listarDTO() {
        return tarjetaService.listarDTO();
    }

}
