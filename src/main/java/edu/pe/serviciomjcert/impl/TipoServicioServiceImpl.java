package edu.pe.serviciomjcert.impl;

import edu.pe.serviciomjcert.model.TipoServicio;
import edu.pe.serviciomjcert.repo.ITipoServicioRepo;
import edu.pe.serviciomjcert.service.ITipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoServicioServiceImpl extends ICRUDServiceImpl <TipoServicio, Integer> implements ITipoServicioService {

    @Autowired
    private ITipoServicioRepo repo;

    @Override
    protected JpaRepository<TipoServicio, Integer> getRepo() {
        return repo;
    }
}
