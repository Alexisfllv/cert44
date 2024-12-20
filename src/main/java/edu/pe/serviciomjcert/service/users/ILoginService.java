package edu.pe.serviciomjcert.service.users;

import edu.pe.serviciomjcert.model.users.Usuario;

public interface ILoginService {

    //verificar
    Usuario verificarNombreUsuario(String usuario);

}
