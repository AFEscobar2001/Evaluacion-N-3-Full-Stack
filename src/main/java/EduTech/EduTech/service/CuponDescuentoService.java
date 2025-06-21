package EduTech.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import EduTech.EduTech.model.CuponDescuento;
import EduTech.EduTech.repository.CuponDescuentoRepository;

//Realizado por: Felipe Escobar

@Service
public class CuponDescuentoService {
    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;

    public String almacenar(CuponDescuento cuponDescuento) {
        if (cuponDescuento.getCodigo() == null || cuponDescuento.getCodigo().isBlank()) {
            return "El código del cupón es obligatorio.";
        }

        if (cuponDescuento.getDescuento() <= 0) {
            return "El descuento debe ser mayor a 0.";
        }

        CuponDescuento existente = cuponDescuentoRepository.findByCodigo(cuponDescuento.getCodigo());
        if (existente != null) {
            return "Ya existe un cupón con el código '" + cuponDescuento.getCodigo() + "'.";
        }

        cuponDescuentoRepository.save(cuponDescuento);
        return "Cupón registrado correctamente.";
    }

    public String aplicarCupon(String codigo) {
        CuponDescuento cuponDescuento = cuponDescuentoRepository.findByCodigo(codigo);

        if (cuponDescuento != null && cuponDescuento.isActivo()) {
            return "Cupón aplicado con éxito: " + cuponDescuento.getDescuento() + " de descuento.";
        } else {
            return "El cupón no existe o está inactivo.";
        }
    }

    @GetMapping
    public List<CuponDescuento> listar() {
        return cuponDescuentoRepository.findAll();
    }

}
