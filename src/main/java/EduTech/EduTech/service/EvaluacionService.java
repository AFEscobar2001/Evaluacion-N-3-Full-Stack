package EduTech.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduTech.EduTech.dto.EvaluacionDTO;
import EduTech.EduTech.model.Evaluacion;
import EduTech.EduTech.repository.ContenidoRepository;
import EduTech.EduTech.repository.EvaluacionRepository;
import EduTech.EduTech.repository.UsuarioRepository;

//Realizado por: Alison Aranda

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String almacenar(Evaluacion evaluacion) {
        if (evaluacion.getNota() < 1.0 || evaluacion.getNota() > 7.0) {
            return "La nota debe estar entre 1.0 y 7.0.";
        }

        if (evaluacion.getContenido() == null || 
            evaluacion.getContenido().getTitulo() == null || 
            !contenidoRepository.existsById(evaluacion.getContenido().getId())) {
            return "Debe asociar la evaluación a un contenido válido.";
        }

        if (evaluacion.getUsuario() == null || 
            evaluacion.getUsuario().getCorreo() == null || 
            !usuarioRepository.existsById(evaluacion.getUsuario().getCorreo())) {
            return "Debe asociar la evaluación a un usuario válido.";
        }

        evaluacionRepository.save(evaluacion);
        return "Evaluación registrada correctamente.";
    }

    public List<EvaluacionDTO> listarDTO() {
        return evaluacionRepository.findAll()
                .stream()
                .map(EvaluacionDTO::new)
                .toList();
    }
    
}
