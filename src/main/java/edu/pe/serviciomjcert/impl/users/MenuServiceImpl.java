package edu.pe.serviciomjcert.impl.users;


import edu.pe.serviciomjcert.impl.ICRUDServiceImpl;
import edu.pe.serviciomjcert.model.users.Menu;
import edu.pe.serviciomjcert.repo.users.IMenuRepo;
import edu.pe.serviciomjcert.service.users.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ICRUDServiceImpl<Menu,Integer> implements IMenuService {

    @Autowired
    private IMenuRepo repo;


    @Override
    protected JpaRepository<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> listarMenuPorUsuario(String nombre) {
        return repo.listarMenuPorUsuario(nombre);
    }
}
