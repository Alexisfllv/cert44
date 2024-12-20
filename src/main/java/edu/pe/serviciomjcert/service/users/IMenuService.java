package edu.pe.serviciomjcert.service.users;

import edu.pe.serviciomjcert.model.users.Menu;
import edu.pe.serviciomjcert.service.ICRUD;

import java.util.List;

public interface IMenuService extends ICRUD<Menu,Integer> {

    //
    List<Menu> listarMenuPorUsuario(String nombre);
}
