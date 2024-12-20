package edu.pe.serviciomjcert.impl;

import edu.pe.serviciomjcert.model.Cliente;
import edu.pe.serviciomjcert.repo.IClienteRepo;
import edu.pe.serviciomjcert.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl  extends ICRUDServiceImpl<Cliente,Integer> implements IClienteService {


    @Autowired
    private IClienteRepo repo;

    @Override
    protected JpaRepository<Cliente, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Cliente> listarPageable(Pageable page) {
        return repo.findAll(page);
    }
}
