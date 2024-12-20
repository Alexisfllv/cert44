package edu.pe.serviciomjcert.dto.users;

import java.util.List;

public class MenuDTO {


    private Integer idMenu;
    private String icono;
    private String nombre;
    private String url;
    private List<RolDTO> roles;

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDTO> roles) {
        this.roles = roles;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuDTO(String icono, Integer idMenu, String nombre, List<RolDTO> roles, String url) {
        this.icono = icono;
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.roles = roles;
        this.url = url;
    }

    public MenuDTO() {
    }
}
