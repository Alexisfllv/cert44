package edu.pe.serviciomjcert.impl;

import edu.pe.serviciomjcert.model.Solicitud;
import edu.pe.serviciomjcert.repo.ISolicitudRepo;
import edu.pe.serviciomjcert.service.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SolicitudServiceImpl extends ICRUDServiceImpl <Solicitud, Integer> implements ISolicitudService {

    @Autowired
    private ISolicitudRepo repo;

    @Override
    protected JpaRepository<Solicitud, Integer> getRepo() {
        return repo;
    }
}
