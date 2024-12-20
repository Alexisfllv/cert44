package edu.pe.serviciomjcert.impl;

import edu.pe.serviciomjcert.model.Tecnico;
import edu.pe.serviciomjcert.repo.ITecnicoRepo;
import edu.pe.serviciomjcert.service.ITecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TecnicoServiceImpl extends ICRUDServiceImpl <Tecnico, Integer> implements ITecnicoService {

    @Autowired
    private ITecnicoRepo repo;

    @Override
    protected JpaRepository<Tecnico, Integer> getRepo() {
        return repo;
    }
}
