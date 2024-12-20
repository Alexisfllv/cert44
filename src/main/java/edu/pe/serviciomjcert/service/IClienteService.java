package edu.pe.serviciomjcert.service;

import edu.pe.serviciomjcert.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface IClienteService extends ICRUD<Cliente,Integer> {

    // paginadores
    Page<Cliente> listarPageable(Pageable page);
}
