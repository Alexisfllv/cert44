package edu.pe.serviciomjcert.repo.users;

import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.repo.IGenericRepo;

public interface IUsuarioRepo extends IGenericRepo<Usuario,Integer> {


    //from usuario where username =?
    //DerivQuery Query derivado data
    //buscar por username
    Usuario findByUsername(String username);
}
