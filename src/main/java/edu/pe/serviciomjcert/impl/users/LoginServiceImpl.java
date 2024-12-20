package edu.pe.serviciomjcert.impl.users;

import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.repo.users.ILoginRepo;
import edu.pe.serviciomjcert.service.users.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {



    @Autowired
    private ILoginRepo repo;

    @Override
    public Usuario verificarNombreUsuario(String usuario) {
        return repo.verificarNombreUsuario(usuario);
    }


}
