package edu.pe.serviciomjcert.repo.users;

import edu.pe.serviciomjcert.model.users.Menu;
import edu.pe.serviciomjcert.repo.IGenericRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMenuRepo extends IGenericRepo<Menu,Integer> {

    //
    @Query(value="select m.* from menu_rol mr inner join usuario_rol ur on ur.id_rol = mr.id_rol inner join menu m on m.id_menu = mr.id_menu inner join usuario u on u.id_usuario = ur.id_usuario where u.nombre = :nombre", nativeQuery = true)
    List<Menu> listarMenuPorUsuario(@Param("nombre") String nombre);
}
