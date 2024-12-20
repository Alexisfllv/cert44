package edu.pe.serviciomjcert.impl;

import edu.pe.serviciomjcert.model.CitaTiposervicio;
import edu.pe.serviciomjcert.repo.ICitaTipoServicioRepo;
import edu.pe.serviciomjcert.service.ICitaTipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaTipoServicioImpl implements ICitaTipoServicioService {

    @Autowired
    private ICitaTipoServicioRepo repo;

    @Override
    public List<CitaTiposervicio> listarTipoServicioPorCita(Integer idcita) {
        return repo.listarTipodeServicioporCita(idcita);
    }
}
