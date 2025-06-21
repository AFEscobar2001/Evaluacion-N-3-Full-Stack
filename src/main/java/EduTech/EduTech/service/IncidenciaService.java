package EduTech.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduTech.EduTech.dto.IncidenciaDTO;
import EduTech.EduTech.model.Incidencia;
import EduTech.EduTech.repository.IncidenciaRepository;
import EduTech.EduTech.repository.UsuarioRepository;

//Realizado por: Felipe Escobar

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String almacenar(Incidencia incidencia) {
        if (incidencia.getDetalle() == null || incidencia.getDetalle().isBlank()) {
            return "El detalle de la incidencia es obligatorio.";
        }

        if (incidencia.getFecha() == null || incidencia.getFecha().isBlank()) {
            return "La fecha de la incidencia es obligatoria.";
        }

        if (incidencia.getUsuario() == null || !usuarioRepository.existsById(incidencia.getUsuario().getCorreo())) {
            return "Debe asociar la incidencia a un usuario válido.";
        }

        incidenciaRepository.save(incidencia);
        return "Incidencia registrada correctamente.";
    }

    public List<IncidenciaDTO> listarDTO() {
        return incidenciaRepository.findAll()
                .stream()
                .map(IncidenciaDTO::new)
                .toList();
    }

}
