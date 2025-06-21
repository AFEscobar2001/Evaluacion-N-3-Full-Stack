package EduTech.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduTech.EduTech.dto.TarjetaDTO;
import EduTech.EduTech.model.Tarjeta;
import EduTech.EduTech.repository.FormaPagoRepository;
import EduTech.EduTech.repository.TarjetaRepository;
import EduTech.EduTech.repository.UsuarioRepository;

//Realizado por: Alison Aranda

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FormaPagoRepository formaPagoRepository;

    public String almacenar(Tarjeta tarjeta) {
        if (tarjeta.getNumero() == null || tarjeta.getNumero().isBlank()) {
            return "El número de la tarjeta es obligatorio.";
        }

        if (tarjeta.getBanco() == null || tarjeta.getBanco().isBlank()) {
            return "El banco es obligatorio.";
        }

        if (tarjeta.getVencimiento() == null || tarjeta.getVencimiento().isBlank()) {
            return "La fecha de vencimiento es obligatoria.";
        }

        if (tarjeta.getUsuario() == null || !usuarioRepository.existsById(tarjeta.getUsuario().getCorreo())) {
            return "Debe asociar la tarjeta a un usuario válido.";
        }

        if (tarjeta.getFormaPago() == null || !formaPagoRepository.existsById(tarjeta.getFormaPago().getId())) {
            return "Debe asociar la tarjeta a una forma de pago válida.";
        }

        Tarjeta existente = tarjetaRepository.findByNumero(tarjeta.getNumero());
        if (existente != null) {
            return "Ya existe una tarjeta registrada con ese número.";
        }

        tarjetaRepository.save(tarjeta);
        return "Tarjeta registrada correctamente.";
    }

    public List<Tarjeta> listar() {
        return tarjetaRepository.findAll();
    }

    public List<TarjetaDTO> listarDTO() {
        return tarjetaRepository.findAll()
                                .stream()
                                .map(TarjetaDTO::new)
                                .toList();
    }

}
