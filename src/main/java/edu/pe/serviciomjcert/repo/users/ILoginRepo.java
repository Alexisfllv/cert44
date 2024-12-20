package edu.pe.serviciomjcert.repo.users;

import edu.pe.serviciomjcert.model.users.Usuario;
import edu.pe.serviciomjcert.repo.IGenericRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ILoginRepo extends IGenericRepo<Usuario,Integer> {

    //verficarnNombreUsuario
    //req cuando envias tu nombre a confirmar si esta existen o no
    @Query("FROM Usuario us where us.username =:usuario")
    Usuario verificarNombreUsuario(@Param("usuario")String usuario);


}
