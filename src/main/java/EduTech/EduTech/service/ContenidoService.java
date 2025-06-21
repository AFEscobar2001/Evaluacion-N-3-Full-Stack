package EduTech.EduTech.service;

import EduTech.EduTech.dto.ContenidoDTO;
import EduTech.EduTech.model.Contenido;
import EduTech.EduTech.repository.ContenidoRepository;
import EduTech.EduTech.repository.CursoRepository;
import EduTech.EduTech.repository.ProveedorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Realizado por: Felipe Escobar

@Service
public class ContenidoService {

@Autowired
private ContenidoRepository contenidoRepository;

@Autowired
private CursoRepository cursoRepository;

@Autowired
private ProveedorRepository proveedorRepository;

    public String almacenar(Contenido contenido) {
        if (contenido.getTitulo() == null || contenido.getTitulo().isBlank()) {
            return "El título del contenido es obligatorio.";
        }

        if (contenido.getDescripcion() == null || contenido.getDescripcion().isBlank()) {
            return "La descripción del contenido es obligatoria.";
        }

        if (contenido.getCurso() == null || !cursoRepository.existsById(contenido.getCurso().getId())) {
            return "Debe asociar un curso válido al contenido.";
        }

        if (contenido.getProveedor() == null || !proveedorRepository.existsById(contenido.getProveedor().getId())) {
            return "Debe asociar un proveedor válido al contenido.";
        }

        Contenido existente = contenidoRepository.findByTitulo(contenido.getTitulo());

        if (existente != null) {
            return "Ya existe un contenido con el título '" + contenido.getTitulo() + "'.";
        }

        contenidoRepository.save(contenido);
        return "Contenido '" + contenido.getTitulo() + "' guardado correctamente.";
    }

    public List<ContenidoDTO> listarDTO() {
        return contenidoRepository.findAll()
                                .stream()
                                .map(ContenidoDTO::new)
                                .toList();
    }

}
